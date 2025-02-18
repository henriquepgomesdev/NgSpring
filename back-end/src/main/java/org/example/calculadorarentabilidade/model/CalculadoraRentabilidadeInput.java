package org.example.calculadorarentabilidade.model;

import org.example.movimentacao.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CalculadoraRentabilidadeInput(LocalDate data, Long idAtivo) {
}
