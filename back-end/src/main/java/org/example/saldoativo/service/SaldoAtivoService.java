package org.example.saldoativo.service;


import org.example.configuracaoativo.service.ConfiguracaoAtivoService;
import org.example.movimentacao.domain.Movimentacao;
import org.example.movimentacao.enums.TipoMovimentacao;
import org.example.movimentacao.model.MovimentacaoInput;
import org.example.movimentacao.model.MovimentacaoOutput;
import org.example.movimentacao.model.SaldoAtivoOutput;
import org.example.movimentacao.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaldoAtivoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private ConfiguracaoAtivoService configuracaoAtivoService;


    public List<SaldoAtivoOutput> calculaSaldo(){
        return repository.calcularSaldoPorConfiguracaoAtivo();
    }
}
