package org.example.financeiro.movimentacao.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum TipoMovimentacao {

    APORTE(1, "Aporte", TipoMovimentacaoFinanceiro.ENTRADA),
    RENDIMENTO(2, "Rendimento", TipoMovimentacaoFinanceiro.ENTRADA),
    RESGATE(3, "Resgate", TipoMovimentacaoFinanceiro.SAIDA);

    private final int id;
    private final String description;
    private final TipoMovimentacaoFinanceiro tipoMovimentacaoFinanceiro;

    @JsonValue
    public int getId() {
        return id;
    }

    @JsonCreator
    public static TipoMovimentacao fromId(int id) {
        return Stream.of(TipoMovimentacao.values())
                .filter(tipo -> tipo.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("TipoMovimentacao inválido: " + id));
    }

}


