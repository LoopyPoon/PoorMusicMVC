package com.example.poormusic.controllers;

import com.example.poormusic.entity.User;
import com.example.poormusic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsersList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

//    @GetMapping("/addTrackForm")
//    public ModelAndView addTrackForm() {
//        ModelAndView mav = new ModelAndView("add-track-form");
//
//    }
}
