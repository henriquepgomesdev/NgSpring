package org.example.financeiro.saldoativo.service;


import org.example.financeiro.configuracaoativo.service.ConfiguracaoAtivoService;
import org.example.user.UsersAutenticatedUtils;
import org.example.financeiro.movimentacao.model.SaldoAtivoOutput;
import org.example.financeiro.movimentacao.repository.MovimentacaoRepository;
import org.example.utils.CalculadoraIR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaldoAtivoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private ConfiguracaoAtivoService configuracaoAtivoService;

    @Autowired
    private UsersAutenticatedUtils usersAutenticatedUtils;

    public List<SaldoAtivoOutput> calculaSaldo(LocalDate dataInicial, LocalDate dataFinal) {

        List<SaldoAtivoOutput> saldos = repository.calcularSaldoPorConfiguracaoAtivo(dataInicial, dataFinal, usersAutenticatedUtils.getAuthenticatedUserId());
        saldos.forEach(CalculadoraIR::calcularIR);
        return saldos;
    }
}
