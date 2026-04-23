package org.example.financeiro.selic.mapper;

import org.example.financeiro.selic.domain.Selic;
import org.example.financeiro.selic.model.SelicDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SelicMapper {
    SelicDto toDTO(Selic entity);

    Selic toEntity(SelicDto dto);
}
