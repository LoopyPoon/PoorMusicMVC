package com.example.poormusic.mapper;

import com.example.poormusic.dto.GenreDto;
import com.example.poormusic.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ArtistMapper.class, AlbumMapper.class})
public interface GenreMapper extends BaseMapper<Genre, GenreDto> {
    @Override
    @Mapping(source = "artists", target = "artists", ignore = true)
    @Mapping(source = "albums", target = "albums", ignore = true)
    GenreDto toDto(Genre entity);

    @Override
    @Mapping(source = "artists", target = "artists", ignore = true)
    @Mapping(source = "albums", target = "albums", ignore = true)
    Genre toEntity(GenreDto dto);

    @Override
    @Mapping(source = "artists", target = "artists", ignore = true)
    @Mapping(source = "albums", target = "albums", ignore = true)
    void updateModel(GenreDto dto, @MappingTarget Genre entity);
}
