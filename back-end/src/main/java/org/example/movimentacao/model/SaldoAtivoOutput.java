package org.example.movimentacao.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaldoAtivoOutput(LocalDate dataInicio, LocalDate data, String ativo, Long idAtivo, Long idMovimentacao, BigDecimal valor, BigDecimal rendimento, BigDecimal rendimentoTotal) {
}
