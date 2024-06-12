package com.example.poormusic.service;

import com.example.poormusic.dto.UserDto;
import com.example.poormusic.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(UserDto userDto);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<UserDto> findAllUsers();

    List<User> findAll();
}
