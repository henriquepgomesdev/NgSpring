package org.example.financeiro.movimentacao.mapper;

import org.example.financeiro.movimentacao.domain.Movimentacao;
import org.example.financeiro.movimentacao.model.MovimentacaoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimentacaoMapper {
    MovimentacaoDto toDTO(Movimentacao entity);

    Movimentacao toEntity(MovimentacaoDto dto);
}
