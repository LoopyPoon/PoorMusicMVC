package com.example.poormusic.controllers;

import com.example.poormusic.repository.PlaylistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class MusicController {

    @Autowired
    private PlaylistRepository playlistRepository;

    @GetMapping("/playlists")
    public ModelAndView getAllPlaylists() {
        log.info("playlist -> connections");
        ModelAndView mav = new ModelAndView("playlists");
        mav.addObject("playlists", playlistRepository.findAll());
        return mav;
    }


}
