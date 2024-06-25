package com.example.poormusic.mapper;

import com.example.poormusic.dto.PlaylistDto;
import com.example.poormusic.entity.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TrackMapper.class})
public interface PlaylistMapper extends BaseMapper<Playlist, PlaylistDto> {

    @Override
    @Mapping(source = "user", target = "user", ignore = true)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    PlaylistDto toDto(Playlist entity);

    @Override
    @Mapping(source = "user", target = "user", ignore = true)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    Playlist toEntity(PlaylistDto dto);

    @Override
    @Mapping(source = "user", target = "user", ignore = true)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    void updateModel(PlaylistDto dto, @MappingTarget Playlist entity);
}
