package com.example.poormusic.controllers;

import com.example.poormusic.dto.UserDto;
import com.example.poormusic.entity.User;
import com.example.poormusic.service.user_service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


@Slf4j
@Controller
public class SecurityController {

    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        Optional<User> existingUserByEmail = userService.findByEmail(userDto.getEmail());
        if (existingUserByEmail.isPresent()) {
            result.rejectValue("email", null,
                    "На этот адрес электронной почты уже зарегестрирована учетная запись");
        }

        Optional<User> existingUserByUsername = userService.findByUsername(userDto.getUsername());
        if (existingUserByUsername.isPresent()) {
            result.rejectValue("username", null,
                    "Этот ник уже занят");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
}
