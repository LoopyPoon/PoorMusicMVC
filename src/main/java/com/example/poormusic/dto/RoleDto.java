package com.example.poormusic.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class RoleDto extends BaseDto {

    private Long id;

    @NotBlank
    public String name;

    public Set<UserDto> users;

}
