package com.example.poormusic.mapper;

import com.example.poormusic.dto.UserDto;
import com.example.poormusic.entity.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, PlaylistMapper.class})
public interface UserMapper extends BaseMapper<User, UserDto> {

    @Override
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "playlists", target = "playlists")
    UserDto toDto(User entity);

    @Override
    @InheritConfiguration
    User toEntity(UserDto dto);

    @Override
    @InheritConfiguration
    void updateModel(UserDto dto, @MappingTarget User entity);
}
