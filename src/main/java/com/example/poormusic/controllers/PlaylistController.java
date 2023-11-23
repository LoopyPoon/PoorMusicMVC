package com.example.poormusic.controllers;

import com.example.poormusic.entity.Playlist;
import com.example.poormusic.entity.User;
import com.example.poormusic.repository.PlaylistRepository;
import com.example.poormusic.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
//@RequestMapping("/users")
public class PlaylistController {


    private final PlaylistRepository playlistRepository;

    private final UserRepository userRepository;

    public PlaylistController(PlaylistRepository playlistRepository,
                              UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/playlists")
    public ModelAndView getAllPlaylists() {
        log.info("playlist -> connections");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsernameOrEmail(auth.getName(), auth.getName());
        ModelAndView mav = new ModelAndView("playlists");
        List<Playlist> playlistList = playlistRepository.findAllByUserId(user.orElseThrow().getId());
        mav.addObject("playlists", playlistList);
        mav.addObject("user", user);
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsernameOrEmail(auth.getName(), auth.getName());
//        user.orElseThrow().addPlaylist(playlist);
        user.ifPresent(playlist::addUser);


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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsernameOrEmail(auth.getName(), auth.getName());

        user.orElseThrow().deletePlaylist(playlistId);
        playlistRepository.deleteById(playlistId);
        return "redirect:/playlists";
    }

    @Deprecated
    @GetMapping("/playlistsOld")
    public ModelAndView getAllPlaylists2() {
        ModelAndView mav = new ModelAndView("playlists");
        mav.addObject("playlistsOld", playlistRepository.findAll());
        return mav;
    }


    
}
