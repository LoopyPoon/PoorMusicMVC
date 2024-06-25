package com.example.poormusic.mapper;

import com.example.poormusic.dto.ArtistDto;
import com.example.poormusic.entity.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AlbumMapper.class, GenreMapper.class, TrackMapper.class})
public interface ArtistMapper extends BaseMapper<Artist, ArtistDto> {
    @Override
    @Mapping(source = "albums", target = "albums", ignore = true)
    @Mapping(source = "genres", target = "genres", ignore = true)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    ArtistDto toDto(Artist entity);

    @Override
    @Mapping(source = "albums", target = "albums", ignore = true)
    @Mapping(source = "genres", target = "genres", ignore = true)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    Artist toEntity(ArtistDto dto);

    @Override
    @Mapping(source = "albums", target = "albums", ignore = true)
    @Mapping(source = "genres", target = "genres", ignore = true)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    void updateModel(ArtistDto dto, @MappingTarget Artist entity);
}
