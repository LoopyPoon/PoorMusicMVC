package com.example.poormusic.mapper;

import com.example.poormusic.dto.PlaylistDto;
import com.example.poormusic.entity.Playlist;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TrackMapper.class})
public interface PlaylistMapper extends BaseMapper<Playlist, PlaylistDto> {

    @Override
    @Mapping(source = "user", target = "user")
    @Mapping(source = "tracks", target = "tracks")
    PlaylistDto toDto(Playlist entity);

    @Override
    @InheritConfiguration
    Playlist toEntity(PlaylistDto dto);

    @Override
    @InheritConfiguration
    void updateModel(PlaylistDto dto, @MappingTarget Playlist entity);
}
