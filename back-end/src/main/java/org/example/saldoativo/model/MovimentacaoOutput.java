package org.example.saldoativo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovimentacaoOutput(LocalDate data, BigDecimal valor, String ativo, String tipoMovimentacao) {
}
