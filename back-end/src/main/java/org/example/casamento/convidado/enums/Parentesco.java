package org.example.casamento.convidado.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum Parentesco {

    PAI(3, "Pai"),
    MÃE(4, "Mãe"),
    IRMÃO(5, "Irmão(ã)"),
    AVÔ(6, "Avô/Avó"),
    TIO(7, "Tio(a)"),
    PRIMO(8, "Primo(a)"),
    AMIGO(9, "Amigo(a)"),
    PADRINHO(10, "Padrinho/Madrinha"),
    FILHO(11, "Filho(a)"),
    SOBRINHO(12, "Sobrinho(a)"),
    CONHECIDO(13, "Conhecido(a)"),
    OUTRO(14, "Outro");

    private final int id;
    private final String descricao;

    @JsonValue
    public int getId() {
        return id;
    }

    @JsonCreator
    public static Parentesco fromId(int id) {
        return Stream.of(Parentesco.values())
                .filter(tipo -> tipo.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Parentesco inválido: " + id));
    }
}
