package org.example.movimentacao.model;

import org.example.movimentacao.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovimentacaoOutput(LocalDate data, BigDecimal valor, String ativo, String tipoMovimentacao) {
}
