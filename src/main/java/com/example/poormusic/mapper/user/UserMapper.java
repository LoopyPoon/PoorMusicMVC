package com.example.poormusic.mapper.user;

import com.example.poormusic.dto.user.UserDto;
import com.example.poormusic.dto.user.UserSummaryDto;
import com.example.poormusic.entity.User;
import com.example.poormusic.mapper.AlbumMapper;
import com.example.poormusic.mapper.BaseMapper;
import com.example.poormusic.mapper.RoleMapper;
import com.example.poormusic.mapper.playlist.PlaylistMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, PlaylistMapper.class, AlbumMapper.class})
public interface UserMapper extends BaseMapper<User, UserDto> {

    @Override
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "playlists", target = "playlists", ignore = true)
    @Mapping(source = "albums", target = "albums", ignore = true)
    UserDto toDto(User entity);

    @Override
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "playlists", target = "playlists", ignore = true)
    @Mapping(source = "albums", target = "albums", ignore = true)
    User toEntity(UserDto dto);

    @Override
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "playlists", target = "playlists", ignore = true)
    @Mapping(source = "albums", target = "albums", ignore = true)
    void updateModel(UserDto dto, @MappingTarget User entity);

    UserSummaryDto toSummaryDto(User entity);
}
