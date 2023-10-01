package com.example.poormusic.service;

import com.example.poormusic.dto.UserDto;
import com.example.poormusic.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findUser(String email);

    List<UserDto> findAllUsers();
}
