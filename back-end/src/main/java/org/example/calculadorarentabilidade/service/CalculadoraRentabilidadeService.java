package org.example.calculadorarentabilidade.service;


import org.example.calculadorarentabilidade.model.CalculadoraRentabilidadeInput;
import org.example.configuracaoativo.domain.ConfiguracaoAtivo;
import org.example.configuracaoativo.service.ConfiguracaoAtivoService;
import org.example.movimentacao.domain.Movimentacao;
import org.example.movimentacao.enums.TipoMovimentacao;
import org.example.movimentacao.model.SaldoAtivoOutput;
import org.example.movimentacao.repository.MovimentacaoRepository;
import org.example.movimentacao.service.MovimentacaoService;
import org.example.saldoativo.service.SaldoAtivoService;
import org.example.utils.DiasUteisBrasil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.example.utils.DiasUteisBrasil.contarDiasUteis;

@Service
public class CalculadoraRentabilidadeService {

    @Autowired
    private SaldoAtivoService saldoAtivoService;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private ConfiguracaoAtivoService configuracaoAtivoService;


    public void calculaValor(CalculadoraRentabilidadeInput calculadoraRentabilidadeInput) {
        List<SaldoAtivoOutput> saldos = saldoAtivoService.calculaSaldo();

        for (SaldoAtivoOutput saldo : saldos) {
            ConfiguracaoAtivo configuracaoAtivo = configuracaoAtivoService.findById(saldo.idAtivo());

            LocalDate dataInicial = saldo.data();
            LocalDate dataFinal = calculadoraRentabilidadeInput.data();
            boolean contarDiasUteis = configuracaoAtivo.isSomenteDiasUteis();
            BigDecimal valorAtual = saldo.valor();
            BigDecimal rentabilidadeDiaria = configuracaoAtivo.getRendimentoDia(); // Rentabilidade diária %

            Movimentacao movimentacaoOrigem = movimentacaoService.findById(saldo.idMovimentacao());

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

                movimentacaoService.salvarMovimentacao(novaMovimentacao);

                // Avançar para o próximo dia
                dataAtual = dataAtual.plusDays(1);
            }
        }
    }


}
