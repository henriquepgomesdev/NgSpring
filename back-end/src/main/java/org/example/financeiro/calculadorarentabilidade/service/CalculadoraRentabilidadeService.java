package org.example.financeiro.calculadorarentabilidade.service;


import org.example.financeiro.calculadorarentabilidade.model.CalculadoraRentabilidadeInput;
import org.example.financeiro.configuracaoativo.domain.ConfiguracaoAtivo;
import org.example.financeiro.configuracaoativo.service.ConfiguracaoAtivoService;
import org.example.financeiro.movimentacao.domain.Movimentacao;
import org.example.financeiro.movimentacao.enums.TipoMovimentacao;
import org.example.financeiro.movimentacao.model.SaldoAtivoOutput;
import org.example.financeiro.movimentacao.service.MovimentacaoService;
import org.example.financeiro.saldoativo.service.SaldoAtivoService;
import org.example.user.UsersAutenticatedUtils;
import org.example.user.repository.UserRepository;
import org.example.utils.DiasUteisBrasil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class CalculadoraRentabilidadeService {

    @Autowired
    private SaldoAtivoService saldoAtivoService;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private ConfiguracaoAtivoService configuracaoAtivoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersAutenticatedUtils usersAutenticatedUtils;


    public void calculaValor(CalculadoraRentabilidadeInput calculadoraRentabilidadeInput) {
        List<SaldoAtivoOutput> saldos = saldoAtivoService.calculaSaldo(null, calculadoraRentabilidadeInput.data());

        for (SaldoAtivoOutput saldo : saldos) {
            ConfiguracaoAtivo configuracaoAtivo = configuracaoAtivoService.findById(saldo.getIdAtivo());

            LocalDate dataInicial = saldo.getData();
            LocalDate dataFinal = calculadoraRentabilidadeInput.data();
            boolean contarDiasUteis = configuracaoAtivo.isSomenteDiasUteis();
            BigDecimal valorAtual = saldo.getValorBruto();
            BigDecimal rentabilidadeDiaria = configuracaoAtivo.getRendimentoDia(); // Rentabilidade diária %

            Movimentacao movimentacaoOrigem = movimentacaoService.findById(saldo.getIdMovimentacao());

            // Iterar sobre cada dia e criar uma movimentação
            LocalDate dataAtual = dataInicial.plusDays(1); // Começa no dia seguinte ao saldo original

            while (!dataAtual.isAfter(dataFinal)) {
                // Se contar apenas dias úteis, pula fins de semana e feriados
                if (contarDiasUteis && !DiasUteisBrasil.isDiaUtil(dataAtual)) {
                    dataAtual = dataAtual.plusDays(1);
                    continue;
                }

                // Aplicar a rentabilidade ao valor atual
                BigDecimal rendimento = valorAtual.multiply(rentabilidadeDiaria.divide(BigDecimal.valueOf(100)))
                        .setScale(2, RoundingMode.HALF_UP);
                valorAtual = valorAtual.add(rendimento);

                // Criar e salvar nova movimentação
                Movimentacao novaMovimentacao = new Movimentacao();
                novaMovimentacao.setConfiguracaoAtivo(configuracaoAtivo);
                novaMovimentacao.setTipoMovimentacao(TipoMovimentacao.RENDIMENTO);
                novaMovimentacao.setData(dataAtual);
                novaMovimentacao.setValor(rendimento);
                novaMovimentacao.setMovimentacaoOrigem(movimentacaoOrigem);
                novaMovimentacao.setUser(userRepository.getReferenceById(usersAutenticatedUtils.getAuthenticatedUserId()));

                movimentacaoService.salvarMovimentacao(novaMovimentacao);

                // Avançar para o próximo dia
                dataAtual = dataAtual.plusDays(1);
            }
        }
    }


}
