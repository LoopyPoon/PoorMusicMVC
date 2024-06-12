package com.example.poormusic.mapper;

public interface BaseMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    void updateModel(D dto, E entity);
}
