package org.example.financeiro.cdi.mapper;

import org.example.financeiro.cdi.domain.Cdi;
import org.example.financeiro.cdi.model.CdiDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CdiMapper {
    CdiDto toDTO(Cdi entity);

    Cdi toEntity(CdiDto dto);
}
