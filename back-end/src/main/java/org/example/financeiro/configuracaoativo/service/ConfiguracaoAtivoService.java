package org.example.financeiro.configuracaoativo.service;


import org.example.financeiro.configuracaoativo.domain.ConfiguracaoAtivo;
import org.example.financeiro.configuracaoativo.mapper.ConfiguracaoAtivoMapper;
import org.example.financeiro.configuracaoativo.model.ConfiguracaoAtivoInput;
import org.example.financeiro.configuracaoativo.repository.ConfiguracaoAtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfiguracaoAtivoService {

    @Autowired
    private ConfiguracaoAtivoRepository repository;

    @Autowired
    private ConfiguracaoAtivoMapper mapper;

    public void create(ConfiguracaoAtivoInput input) {
        repository.save(new ConfiguracaoAtivo(input));
    }

    public ConfiguracaoAtivo findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Configuração não encontrada!"));
    }

    public List<ConfiguracaoAtivo> getAll() {
        return repository.findAll();
    }
}
