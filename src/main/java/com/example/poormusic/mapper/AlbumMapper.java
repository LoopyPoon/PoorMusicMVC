package com.example.poormusic.mapper;

import com.example.poormusic.dto.AlbumDto;
import com.example.poormusic.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ArtistMapper.class, GenreMapper.class, TrackMapper.class, UserMapper.class})
public interface AlbumMapper extends BaseMapper<Album, AlbumDto> {
    @Override
    @Mapping(source = "artists", target = "artists")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "tracks", target = "tracks")
    @Mapping(source = "users", target = "users")
    AlbumDto toDto(Album entity);

    @Override
    @Mapping(source = "artists", target = "artists")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "tracks", target = "tracks")
    @Mapping(source = "users", target = "users")
    Album toEntity(AlbumDto dto);

    @Override
    @Mapping(source = "artists", target = "artists")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "tracks", target = "tracks")
    @Mapping(source = "users", target = "users")
    void updateModel(AlbumDto dto, @MappingTarget Album entity);
}
