package org.example.financeiro.selic.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.financeiro.selic.model.SelicInput;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "selic")
@Entity(name = "selic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Selic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_selic")
    @SequenceGenerator(name = "seq_selic", sequenceName = "seq_selic", allocationSize = 1)
    private Long id;

    private LocalDate data;

    private BigDecimal valor;

    public Selic(SelicInput input) {
        this.valor = input.valor();
        this.data = input.data();
    }
}

