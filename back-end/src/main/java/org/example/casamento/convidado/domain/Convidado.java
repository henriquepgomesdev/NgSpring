package org.example.casamento.convidado.domain;

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
import org.example.casamento.convidado.enums.Parentesco;
import org.example.casamento.noivo.domain.Noivo;
import org.example.user.domain.User;

@Table(name = "convidado")
@Entity(name = "convidado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Convidado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_convidado")
    @SequenceGenerator(name = "seq_convidado", sequenceName = "seq_convidado", allocationSize = 1)
    private Long id;

    private String nomeConvidado;

    @ManyToOne(fetch = FetchType.LAZY) // Define o relacionamento ManyToOne
    @JoinColumn(name = "noivo_id") // Nome da coluna na tabela Movimentacao
    private Noivo noivo;

    private Parentesco parentesco;

    private Boolean confirmaPresenca;

    @ManyToOne(fetch = FetchType.LAZY) // Define o relacionamento ManyToOne
    @JoinColumn(name = "user_id") // Nome da coluna na tabela Movimentacao
    private User user;  // Relacionamento com ConfiguracaoAtivo




//    public Convidado(convidadoInput input, ConfiguracaoAtivo configuracaoAtivo, User user) {
//        this.valorAplicado = input.valorAplicado();
//        this.configuracaoAtivo = configuracaoAtivo;
//        this.valor = input.valor();
//        this.tipoconvidado = input.tipoconvidado();
//        this.data = input.data();
//        this.dataAplicacao = input.dataAplicacao();
//        this.dataVencimento = input.dataVencimento();
//        this.valorAplicado = input.valorAplicado();
//        this.user = user;
//    }
//
//    public void setarconvidadoOrigem() {
//        this.convidadoOrigem = this;
//    }
}

