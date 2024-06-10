package com.example.poormusic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BaseMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    void updateModel(D dto, @MappingTarget E entity);
}
