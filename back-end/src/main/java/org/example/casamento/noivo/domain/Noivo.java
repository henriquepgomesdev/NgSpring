package org.example.casamento.noivo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.user.domain.User;

@Table(name = "noivo")
@Entity(name = "noivo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Noivo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_noivo")
    @SequenceGenerator(name = "seq_noivo", sequenceName = "seq_noivo", allocationSize = 1)
    private Long id;

    private String nomeNoivo;

    @ManyToOne(fetch = FetchType.LAZY) // Define o relacionamento ManyToOne
    @JoinColumn(name = "user_id") // Nome da coluna na tabela Movimentacao
    private User user;  // Relacionamento com ConfiguracaoAtivo

}

