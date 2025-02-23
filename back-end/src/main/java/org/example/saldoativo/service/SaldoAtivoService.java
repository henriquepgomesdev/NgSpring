package org.example.saldoativo.service;


import org.example.configuracaoativo.service.ConfiguracaoAtivoService;
import org.example.infra.security.UsersAutenticatedUtils;
import org.example.movimentacao.model.SaldoAtivoOutput;
import org.example.movimentacao.repository.MovimentacaoRepository;
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
