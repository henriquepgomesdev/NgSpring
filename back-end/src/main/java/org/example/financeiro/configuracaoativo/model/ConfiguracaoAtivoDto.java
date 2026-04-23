package org.example.financeiro.configuracaoativo.model;

import java.math.BigDecimal;

public record ConfiguracaoAtivoDto(String nome, BigDecimal rendimentoDia, BigDecimal rendimentoMes,
                                   boolean somenteDiasUteis) {
}
