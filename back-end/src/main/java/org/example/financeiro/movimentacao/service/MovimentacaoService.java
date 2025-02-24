package org.example.financeiro.movimentacao.service;


import org.example.financeiro.configuracaoativo.service.ConfiguracaoAtivoService;
import org.example.financeiro.movimentacao.domain.Movimentacao;
import org.example.financeiro.movimentacao.enums.TipoMovimentacao;
import org.example.financeiro.movimentacao.model.MovimentacaoInput;
import org.example.financeiro.movimentacao.model.MovimentacaoOutput;
import org.example.user.UsersAutenticatedUtils;
import org.example.financeiro.movimentacao.repository.MovimentacaoRepository;
import org.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfiguracaoAtivoService configuracaoAtivoService;

    @Autowired
    private UsersAutenticatedUtils usersAutenticatedUtils;

    public void create(MovimentacaoInput input) {
        Movimentacao movimentacao = new Movimentacao(input, configuracaoAtivoService.findById(input.idAtivo()), userRepository.getReferenceById(usersAutenticatedUtils.getAuthenticatedUserId()));
        movimentacao.setarMovimentacaoOrigem();
        repository.save(movimentacao);
    }

    public List<Movimentacao> getAll() {
        return repository.findAll();
    }

    public List<MovimentacaoOutput> getAllMovimentacoes() {
        List<Movimentacao> movimentacoes = repository.findAllByUserId(usersAutenticatedUtils.getAuthenticatedUserId());  // ou sua lógica para buscar as movimentações

        return movimentacoes.stream()
                .map(movimentacao -> new MovimentacaoOutput(
                        movimentacao.getData(),
                        movimentacao.getValor(),
                        movimentacao.getConfiguracaoAtivo().getNome(),
                        movimentacao.getTipoMovimentacao().getDescription()
                ))
                .collect(Collectors.toList());
    }

    public List<Movimentacao> findByIdAtivoAndTipoMovimentacao(Long idAtivo, TipoMovimentacao tipoMovimentacao) {
        return repository.findByConfiguracaoAtivoIdAndTipoMovimentacaoOrderByDataDesc(idAtivo, tipoMovimentacao);
    }

    public void salvarMovimentacao(Movimentacao movimentacao) {
        repository.save(movimentacao);
    }

    public Movimentacao findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Configuração não encontrada!"));
    }
}
