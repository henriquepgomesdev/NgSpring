package org.example.utils;

import org.example.movimentacao.model.SaldoAtivoOutput;

import java.math.BigDecimal;

public class CalculadoraIR {

    // Função para calcular o IR
    public static void calcularIR(SaldoAtivoOutput saldo) {
        // Calcula o rendimento (valorBruto - valorInicial)
        BigDecimal valorBruto = saldo.getValorBruto();
        BigDecimal valorInicial = saldo.getValorInicial();
        BigDecimal rendimento = valorBruto.subtract(valorInicial);

        // Define a alíquota conforme o prazo
        Integer prazo = saldo.getQuantidadeDias();
        BigDecimal aliquota;

        if(prazo == 0) {
            aliquota = new BigDecimal("0.0");
        }else if (prazo <= 180) {
            aliquota = new BigDecimal("0.225"); // 22,5% para até 180 dias
        } else if (prazo <= 360) {
            aliquota = new BigDecimal("0.20"); // 20% para de 181 a 360 dias
        } else if (prazo <= 720) {
            aliquota = new BigDecimal("0.175"); // 17,5% para de 361 a 720 dias
        } else {
            aliquota = new BigDecimal("0.15"); // 15% para acima de 720 dias
        }

        // Calcula o IR (rendimento * aliquota)
        BigDecimal IR = rendimento.multiply(aliquota);

        // Calcula o rendimento líquido e o valor líquido
        BigDecimal rendimentoLiquido = valorBruto.subtract(valorInicial).subtract(IR);
        BigDecimal valorLiquido = valorBruto.subtract(IR);

        // Atualiza os valores no objeto saldo
        saldo.setRendimentoLiquido(rendimentoLiquido);
        saldo.setValorLiquido(valorLiquido);
    }
}