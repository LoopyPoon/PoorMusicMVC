package com.example.poormusic.service;

import com.example.poormusic.dto.UserDto;
import com.example.poormusic.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findByEmail(String email);

    User findByUsername(String username);

    List<UserDto> findAllUsers();

    List<User> findAll();
}
