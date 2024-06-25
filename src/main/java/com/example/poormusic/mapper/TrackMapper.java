package com.example.poormusic.mapper;

import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.entity.Track;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ArtistMapper.class, PlaylistMapper.class, AlbumMapper.class})
public interface TrackMapper extends BaseMapper<Track, TrackDto> {

    @Override
    @Mapping(source = "artists", target = "artists", ignore = true)
    @Mapping(source = "playlists", target = "playlists", ignore = true)
    @Mapping(source = "album", target = "album", ignore = true)
    TrackDto toDto(Track entity);

    @Override
    @Mapping(source = "artists", target = "artists", ignore = true)
    @Mapping(source = "playlists", target = "playlists", ignore = true)
    @Mapping(source = "album", target = "album", ignore = true)
    Track toEntity(TrackDto dto);

    @Override
    @Mapping(source = "artists", target = "artists", ignore = true)
    @Mapping(source = "playlists", target = "playlists", ignore = true)
    @Mapping(source = "album", target = "album", ignore = true)
    void updateModel(TrackDto dto, @MappingTarget Track entity);
}
