package org.example.financeiro.configuracaoativo.mapper;

import jakarta.annotation.PostConstruct;
import org.example.financeiro.configuracaoativo.domain.ConfiguracaoAtivo;
import org.example.financeiro.configuracaoativo.model.ConfiguracaoAtivoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfiguracaoAtivoMapper {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        System.out.println("ModelMapper foi injetado? " + (modelMapper != null));
    }

    public ConfiguracaoAtivo toDomain(ConfiguracaoAtivoInput input) {
        return modelMapper.map(input, ConfiguracaoAtivo.class);
    }
}
