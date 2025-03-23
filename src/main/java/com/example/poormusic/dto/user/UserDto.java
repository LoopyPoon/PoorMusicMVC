package com.example.poormusic.dto.user;

import com.example.poormusic.dto.AlbumDto;
import com.example.poormusic.dto.BaseDto;
import com.example.poormusic.dto.RoleDto;
import com.example.poormusic.dto.playlist.PlaylistDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserDto extends BaseDto {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotEmpty(message = "Email should be not empty")
    @Email
    private String email;

    @NotEmpty(message = "Username should be not empty")
    private String username;

    @NotEmpty(message = "Password should be not empty")
    private String password;

    private Set<RoleDto> roles;

    private Set<PlaylistDto> playlists;

    private Set<AlbumDto> albums;
}
