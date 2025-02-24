package org.example.financeiro.movimentacao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaldoAtivoOutput {

    private LocalDate dataInicio;
    private LocalDate data;
    private String ativo;
    private Long idAtivo;
    private Long idMovimentacao;
    private BigDecimal valorBruto;
    private BigDecimal rendimentoBruto;
    private BigDecimal valorInicial;
    private Integer quantidadeDias;
    private BigDecimal rendimentoLiquido;
    private BigDecimal valorLiquido;
}
