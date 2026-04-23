package org.example.financeiro.selic.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SelicDto(BigDecimal valor, LocalDate data) {
}
