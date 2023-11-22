package com.example.poormusic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty(message = "Email should be not empty")
    @Email
    private String email;

    @NotEmpty(message = "Username should be not empty")
    private String username;

    @NotEmpty(message = "Password should be not empty")
    private String password;
}
