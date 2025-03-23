package com.example.poormusic.service.user_service;

import com.example.poormusic.dto.user.UserDto;
import com.example.poormusic.entity.User;
import org.springframework.security.core.Authentication;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(UserDto userDto);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findUserById(long id);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<UserDto> findAllUsers();

    List<User> findAll();

    User getAuthenticatedUser(Authentication authentication) throws AccessDeniedException;
}
