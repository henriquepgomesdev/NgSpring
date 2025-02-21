package org.example.movimentacao.repository;

import org.example.movimentacao.domain.Movimentacao;
import org.example.movimentacao.enums.TipoMovimentacao;
import org.example.movimentacao.model.SaldoAtivoOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByConfiguracaoAtivoIdAndTipoMovimentacaoOrderByDataDesc(Long idAtivo, TipoMovimentacao tipoMovimentacao);

    @Query("""
                SELECT new org.example.movimentacao.model.SaldoAtivoOutput(MIN(m.data), MAX(m.data), m.configuracaoAtivo.nome, m.configuracaoAtivo.id, m.movimentacaoOrigem.id,
                    COALESCE(SUM(CASE WHEN m.tipoMovimentacao IN ('APORTE', 'RENDIMENTO') THEN m.valor ELSE 0 END), 0) -
                    COALESCE(SUM(CASE WHEN m.tipoMovimentacao = 'RESGATE' THEN m.valor ELSE 0 END), 0) as valor,
                    COALESCE(SUM(CASE WHEN m.tipoMovimentacao = 'RENDIMENTO' THEN m.valor ELSE 0 END), 0) as rendimento,
                    COALESCE(SUM(CASE WHEN m.tipoMovimentacao = 'APORTE' THEN m.valor ELSE 0 END), 0) -
                    COALESCE(SUM(CASE WHEN m.tipoMovimentacao = 'APORTE' THEN m.valorAplicado ELSE 0 END), 0) +
                    COALESCE(SUM(CASE WHEN m.tipoMovimentacao = 'RENDIMENTO' THEN m.valor ELSE 0 END), 0)
                    )
                FROM movimentacao m
                WHERE m.data <= :data
                GROUP BY m.configuracaoAtivo.nome, m.configuracaoAtivo.id, m.movimentacaoOrigem.id
                ORDER BY m.configuracaoAtivo.nome
            """)
    List<SaldoAtivoOutput> calcularSaldoPorConfiguracaoAtivo(LocalDate data);


}
