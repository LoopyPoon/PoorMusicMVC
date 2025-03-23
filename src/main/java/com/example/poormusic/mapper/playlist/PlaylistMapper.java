package com.example.poormusic.mapper.playlist;

import com.example.poormusic.dto.playlist.PlaylistDto;
import com.example.poormusic.dto.playlist.PlaylistSummaryDto;
import com.example.poormusic.entity.Playlist;
import com.example.poormusic.mapper.BaseMapper;
import com.example.poormusic.mapper.TrackMapper;
import com.example.poormusic.mapper.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TrackMapper.class})
public interface PlaylistMapper extends BaseMapper<Playlist, PlaylistDto> {

    @Override
    @Mapping(source = "user", target = "user", ignore = false)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    PlaylistDto toDto(Playlist entity);

    @Override
    @Mapping(source = "user", target = "user", ignore = false)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    Playlist toEntity(PlaylistDto dto);

    @Override
    @Mapping(source = "user", target = "user", ignore = false)
    @Mapping(source = "tracks", target = "tracks", ignore = true)
    void updateModel(PlaylistDto dto, @MappingTarget Playlist entity);

    PlaylistSummaryDto toSummaryDto(Playlist entity);
}
