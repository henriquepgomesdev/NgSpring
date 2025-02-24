package org.example.financeiro.configuracaoativo.domain;

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
import org.example.financeiro.configuracaoativo.model.ConfiguracaoAtivoInput;

import java.math.BigDecimal;

@Table(name = "configuracao_ativo")
@Entity(name = "configuracao_ativo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ConfiguracaoAtivo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_configuracao_ativo")
    @SequenceGenerator(name = "seq_configuracao_ativo", sequenceName = "seq_configuracao_ativo", allocationSize = 1)
    private Long id;

    private String nome;

    private BigDecimal rendimentoDia;

    private BigDecimal rendimentoMes;

    private Integer quantidadeDias;

    private boolean somenteDiasUteis;

    public ConfiguracaoAtivo(ConfiguracaoAtivoInput input) {
        this.nome = input.nome();
        this.rendimentoDia = input.rendimentoDia();
        this.rendimentoMes = input.rendimentoMes();
        this.somenteDiasUteis = input.somenteDiasUteis();
    }
}
