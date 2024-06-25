package com.example.poormusic.mapper;

import com.example.poormusic.dto.AlbumDto;
import com.example.poormusic.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ArtistMapper.class, GenreMapper.class, TrackMapper.class, UserMapper.class})
public interface AlbumMapper extends BaseMapper<Album, AlbumDto> {
    @Override
    @Mapping(source = "artists", target = "artists", ignore = true)
    @Mapping(source = "genre", target = "genre", ignore = true)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    @Mapping(source = "users", target = "users", ignore = true)
    AlbumDto toDto(Album entity);

    @Override
    @Mapping(source = "artists", target = "artists", ignore = true)
    @Mapping(source = "genre", target = "genre", ignore = true)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    @Mapping(source = "users", target = "users", ignore = true)
    Album toEntity(AlbumDto dto);

    @Override
    @Mapping(source = "artists", target = "artists", ignore = true)
    @Mapping(source = "genre", target = "genre", ignore = true)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    @Mapping(source = "users", target = "users", ignore = true)
    void updateModel(AlbumDto dto, @MappingTarget Album entity);
}
