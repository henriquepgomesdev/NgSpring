package org.example.cdi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CdiInput(BigDecimal valor, LocalDate data) {
}
