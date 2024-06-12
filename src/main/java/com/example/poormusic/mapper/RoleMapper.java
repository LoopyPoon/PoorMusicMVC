package com.example.poormusic.mapper;

import com.example.poormusic.dto.RoleDto;
import com.example.poormusic.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RoleMapper extends BaseMapper<Role, RoleDto>{
    @Override
    @Mapping(source = "users", target = "users")
    RoleDto toDto(Role entity);

    @Override
    @Mapping(source = "users", target = "users")
    Role toEntity(RoleDto dto);

    @Override
    @Mapping(source = "users", target = "users")
    void updateModel(RoleDto dto, @MappingTarget Role entity);
}
