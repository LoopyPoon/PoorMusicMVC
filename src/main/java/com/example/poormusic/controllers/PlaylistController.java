package com.example.poormusic.controllers;

import com.example.poormusic.entity.Playlist;
import com.example.poormusic.repository.PlaylistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class PlaylistController {

    @Autowired
    private PlaylistRepository playlistRepository;

    @GetMapping("/playlists")
    public ModelAndView getAllPlaylists() {
        log.info("playlist -> connections");
        ModelAndView mav = new ModelAndView("playlists");
        mav.addObject("playlists", playlistRepository.findAll());
        return mav;
    }

    @GetMapping("/addPlaylistForm")
    public ModelAndView addPlaylistForm() {
        ModelAndView mav = new ModelAndView("add-playlist-form");
        Playlist playlist = new Playlist();
        mav.addObject("playlist", playlist);
        return mav;
    }

    @PostMapping("/savePlaylist")
    public String savePlaylist(@ModelAttribute Playlist playlist) {
        playlistRepository.save(playlist);
        return "redirect:/playlists";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long playlistId) {
        ModelAndView mav = new ModelAndView("add-playlist-form");
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        Playlist playlist = new Playlist();
        if (optionalPlaylist.isPresent()) {
            playlist = optionalPlaylist.get();
        }
        mav.addObject("playlist", playlist);
        return mav;
    }

    @GetMapping("/deletePlaylist")
    public String deletePlaylist(@RequestParam Long playlistId) {
        playlistRepository.deleteById(playlistId);
        return "redirect:/playlists";
    }

    @GetMapping("/playlistsOld")
    public ModelAndView getAllPlaylists2() {
        ModelAndView mav = new ModelAndView("playlists");
        mav.addObject("playlistsOld", playlistRepository.findAll());
        return mav;
    }

    
}
