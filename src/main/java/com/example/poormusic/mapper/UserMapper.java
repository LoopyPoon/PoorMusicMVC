package com.example.poormusic.mapper;

import com.example.poormusic.dto.UserDto;
import com.example.poormusic.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, PlaylistMapper.class})
public interface UserMapper extends BaseMapper<User, UserDto> {

    @Override
    UserDto toDto(User entity);

    @Override
    User toEntity(UserDto dto);

    @Override
    void updateModel(UserDto dto, @MappingTarget User entity);
}
