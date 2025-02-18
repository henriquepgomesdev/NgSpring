package org.example.movimentacao.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum TipoMovimentacaoFinanceiro {

    ENTRADA(1, "Entrada"),
    SAIDA(2, "Saída");

    private final int id;
    private final String description;

    @JsonValue
    public int getId() {
        return id;
    }

    @JsonCreator
    public static TipoMovimentacaoFinanceiro fromId(int id) {
        return Stream.of(TipoMovimentacaoFinanceiro.values())
                .filter(tipo -> tipo.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("TipoMovimentacao inválido: " + id));
    }

}


