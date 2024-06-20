package com.example.poormusic.mapper;

import com.example.poormusic.dto.UserDto;
import com.example.poormusic.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, PlaylistMapper.class, AlbumMapper.class})
public interface UserMapper extends BaseMapper<User, UserDto> {

    @Override
    @Mapping(source = "roles", target = "roles", ignore = true)
    @Mapping(source = "playlists", target = "playlists", ignore = true)
    @Mapping(source = "albums", target = "albums", ignore = true)
    UserDto toDto(User entity);

    @Override
    @Mapping(source = "roles", target = "roles", ignore = true)
    @Mapping(source = "playlists", target = "playlists", ignore = true)
    @Mapping(source = "albums", target = "albums", ignore = true)
    User toEntity(UserDto dto);

    @Override
    @Mapping(source = "roles", target = "roles", ignore = true)
    @Mapping(source = "playlists", target = "playlists", ignore = true)
    @Mapping(source = "albums", target = "albums", ignore = true)
    void updateModel(UserDto dto, @MappingTarget User entity);
}
