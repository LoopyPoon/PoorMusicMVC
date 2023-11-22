package com.example.poormusic.service;

import com.example.poormusic.entity.User;
import com.example.poormusic.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//        User userFindByEmail = userRepository.findByEmail(usernameOrEmail);
//        User userFindByUsername = userRepository.findByUsername(usernameOrEmail);
//        if (userFindByEmail != null) {
//            return new org.springframework.security.core.userdetails.User(userFindByEmail.getEmail(),
//                    userFindByEmail.getPassword(),
//                    userFindByEmail.getRoles().stream()
//                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
//                            .collect(Collectors.toList()));
//        } else if (userFindByUsername != null) {
//            return new org.springframework.security.core.userdetails.User(userFindByUsername.getUsername(),
//                    userFindByUsername.getPassword(),
//                    userFindByUsername.getRoles().stream()
//                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
//                            .collect(Collectors.toList()));
//        } else {
//            throw new UsernameNotFoundException("User not found with email or username: " + usernameOrEmail);
//        }

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).
                orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));
        return new org.springframework.security.core.userdetails.User(usernameOrEmail, user.getPassword(),
                user.getRoles().stream()
                        .map((role) -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toSet()));
    }


}
