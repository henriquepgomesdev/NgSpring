package org.example.movimentacao.model;

import org.example.movimentacao.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovimentacaoInput(LocalDate data, BigDecimal valor, BigDecimal valorAplicado, Long idAtivo, TipoMovimentacao tipoMovimentacao) {
}
