package org.example.financeiro.movimentacao.repository;

import org.example.financeiro.movimentacao.domain.Movimentacao;
import org.example.financeiro.movimentacao.enums.TipoMovimentacao;
import org.example.financeiro.movimentacao.model.SaldoAtivoOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByConfiguracaoAtivoIdAndTipoMovimentacaoOrderByDataDesc(Long idAtivo, TipoMovimentacao tipoMovimentacao);

    @Query("""
                SELECT new org.example.financeiro.movimentacao.model.SaldoAtivoOutput(
                    MIN(m.data),
                    MAX(m.data),
                    m.configuracaoAtivo.nome,
                    m.configuracaoAtivo.id,
                    movimentacaoOrigem.id,
                    COALESCE(SUM(CASE WHEN m.tipoMovimentacao IN ('APORTE', 'RENDIMENTO') THEN m.valor ELSE 0 END), 0) -
                    COALESCE(SUM(CASE WHEN m.tipoMovimentacao = 'RESGATE' THEN m.valor ELSE 0 END), 0) as valorBruto,
                    COALESCE(SUM(CASE WHEN m.tipoMovimentacao = 'APORTE' THEN m.valor ELSE 0 END), 0) -
                    COALESCE(SUM(CASE WHEN m.tipoMovimentacao = 'APORTE' THEN m.valorAplicado ELSE 0 END), 0) +
                    COALESCE(SUM(CASE WHEN m.tipoMovimentacao = 'RENDIMENTO' THEN m.valor ELSE 0 END), 0) as rendimentoBruto,
                    movimentacaoOrigem.valorAplicado as valorInicial,
                    m.configuracaoAtivo.quantidadeDias,
                    null,
                    null
                    )
                FROM movimentacao m
                LEFT JOIN m.movimentacaoOrigem movimentacaoOrigem
                WHERE m.data <= :dataFinal
                 AND m.data >= COALESCE(:dataInicial, m.data)
                 AND m.user.id = :idUser
                GROUP BY m.configuracaoAtivo.nome, m.configuracaoAtivo.id, movimentacaoOrigem.id, movimentacaoOrigem.valorAplicado, m.configuracaoAtivo.quantidadeDias
                ORDER BY m.configuracaoAtivo.nome
            """)
    List<SaldoAtivoOutput> calcularSaldoPorConfiguracaoAtivo(LocalDate dataInicial, LocalDate dataFinal, Long idUser);

    @Query("""
                SELECT m
                FROM movimentacao m
                WHERE m.user.id = :idUser
                
            """)
    List<Movimentacao> findAllByUserId(Long idUser);


}
