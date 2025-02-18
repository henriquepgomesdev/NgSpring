package org.example.saldoativo.model;

import org.example.movimentacao.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovimentacaoInput(LocalDate data, BigDecimal valor, Long idAtivo, TipoMovimentacao tipoMovimentacao) {
}
