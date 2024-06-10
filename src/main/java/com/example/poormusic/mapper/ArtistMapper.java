package com.example.poormusic.mapper;

import com.example.poormusic.dto.ArtistDto;
import com.example.poormusic.entity.Artist;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AlbumMapper.class, GenreMapper.class, TrackMapper.class})
public interface ArtistMapper extends BaseMapper<Artist, ArtistDto> {
    @Override
    @Mapping(source = "albums", target = "albums")
    @Mapping(source = "genres", target = "genres")
    @Mapping(source = "tracks", target = "tracks")
    ArtistDto toDto(Artist entity);

    @Override
    @InheritConfiguration
    Artist toEntity(ArtistDto dto);

    @Override
    @InheritConfiguration
    void updateModel(ArtistDto dto, @MappingTarget Artist entity);
}
