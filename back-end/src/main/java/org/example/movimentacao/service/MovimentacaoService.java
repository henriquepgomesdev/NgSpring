package org.example.movimentacao.service;


import org.example.configuracaoativo.domain.ConfiguracaoAtivo;
import org.example.configuracaoativo.service.ConfiguracaoAtivoService;
import org.example.movimentacao.domain.Movimentacao;
import org.example.movimentacao.enums.TipoMovimentacao;
import org.example.movimentacao.model.MovimentacaoInput;
import org.example.movimentacao.model.MovimentacaoOutput;
import org.example.movimentacao.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private ConfiguracaoAtivoService configuracaoAtivoService;

    public void create(MovimentacaoInput input) {
        repository.save(new Movimentacao(input, configuracaoAtivoService.findById(input.idAtivo())));
    }

    public List<Movimentacao> getAll() {
        return repository.findAll();
    }

    public List<MovimentacaoOutput> getAllMovimentacoes() {
        List<Movimentacao> movimentacoes = repository.findAll();  // ou sua lógica para buscar as movimentações

        return movimentacoes.stream()
                .map(movimentacao -> new MovimentacaoOutput(
                        movimentacao.getData(),
                        movimentacao.getValor(),
                        movimentacao.getConfiguracaoAtivo().getNome(),
                        movimentacao.getTipoMovimentacao().getDescription()
                ))
                .collect(Collectors.toList());
    }

    public List<Movimentacao> findByIdAtivoAndTipoMovimentacao(Long idAtivo, TipoMovimentacao tipoMovimentacao) {
        return repository.findByConfiguracaoAtivoIdAndTipoMovimentacaoOrderByDataDesc(idAtivo, tipoMovimentacao);
    }

    public void salvarMovimentacao(Movimentacao movimentacao) {
        repository.save(movimentacao);
    }

    public Movimentacao findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Configuração não encontrada!"));
    }
}
