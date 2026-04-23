package org.example.financeiro.cdi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CdiDto(BigDecimal valor, LocalDate data) {
}
