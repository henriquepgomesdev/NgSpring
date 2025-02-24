package org.example.financeiro.movimentacao.model;

import org.example.financeiro.movimentacao.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovimentacaoInput(LocalDate data, LocalDate dataAplicacao, LocalDate dataVencimento, BigDecimal valor, BigDecimal valorAplicado, Long idAtivo,
                                TipoMovimentacao tipoMovimentacao) {
}
