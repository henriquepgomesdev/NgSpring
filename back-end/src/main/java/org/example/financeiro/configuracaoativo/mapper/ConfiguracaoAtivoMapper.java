package org.example.financeiro.configuracaoativo.mapper;

import org.example.financeiro.configuracaoativo.domain.ConfiguracaoAtivo;
import org.example.financeiro.configuracaoativo.model.ConfiguracaoAtivoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfiguracaoAtivoMapper {
    ConfiguracaoAtivoDto toDTO(ConfiguracaoAtivo entity);

    ConfiguracaoAtivo toEntity(ConfiguracaoAtivoDto dto);
}
