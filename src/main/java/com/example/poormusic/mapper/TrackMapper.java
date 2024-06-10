package com.example.poormusic.mapper;

import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.entity.Track;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ArtistMapper.class, PlaylistMapper.class, AlbumMapper.class})
public interface TrackMapper extends BaseMapper<Track, TrackDto> {

    @Override
    @Mapping(source = "artists", target = "artists")
    @Mapping(source = "playlists", target = "playlists")
    @Mapping(source = "album", target = "album")
    TrackDto toDto(Track entity);

    @Override
    @InheritConfiguration
    Track toEntity(TrackDto dto);

    @Override
    @InheritConfiguration
    void updateModel(TrackDto dto, @MappingTarget Track entity);
}
