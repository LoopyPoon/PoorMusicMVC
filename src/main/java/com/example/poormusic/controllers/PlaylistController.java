package com.example.poormusic.controllers;

import com.example.poormusic.dto.PlaylistDto;
import com.example.poormusic.entity.Playlist;
import com.example.poormusic.entity.User;
import com.example.poormusic.repository.PlaylistRepository;
import com.example.poormusic.service.PlaylistService;
import com.example.poormusic.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
//@RequestMapping("/users")
public class PlaylistController {


    private final PlaylistRepository playlistRepository;

//    private final UserRepository userRepository;

    private final PlaylistService playlistService;

    private final UserService userService;

    public PlaylistController(PlaylistService playlistService,
                              UserService userService,
                              PlaylistRepository playlistRepository) {
        this.playlistService = playlistService;
        this.userService = userService;
        this.playlistRepository = playlistRepository;
    }

    @GetMapping("/playlists")
    public ModelAndView getAllPlaylists() {
        log.info("playlist -> connections");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsernameOrEmail(auth.getName(), auth.getName());
        ModelAndView mav = new ModelAndView("playlists");
        Set<PlaylistDto> playlistList = playlistService.findAllByUserId(user.orElseThrow().getId());
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
        Optional<User> user = userService.findByUsernameOrEmail(auth.getName(), auth.getName());
//        user.orElseThrow().addPlaylist(playlist);
        user.ifPresent(playlist::addUser);


        playlistService.save(playlist);
        return "redirect:/playlists";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long playlistId) {
        ModelAndView mav = new ModelAndView("add-playlist-form");
        Optional<PlaylistDto> optionalPlaylist = playlistService.findById(playlistId);
        PlaylistDto playlist = new PlaylistDto();
        if (optionalPlaylist.isPresent()) {
            playlist = optionalPlaylist.get();
        }
        mav.addObject("playlist", playlist);
        return mav;
    }

    @GetMapping("/deletePlaylist")
    public String deletePlaylist(@RequestParam Long playlistId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsernameOrEmail(auth.getName(), auth.getName());

        user.orElseThrow().deletePlaylist(playlistId);
        playlistRepository.deleteById(playlistId);
        return "redirect:/playlists";
    }

//    @Deprecated
//    @GetMapping("/playlistsOld")
//    public ModelAndView getAllPlaylists2() {
//        ModelAndView mav = new ModelAndView("playlists");
//        mav.addObject("playlistsOld", playlistRepository.findAll());
//        return mav;
//    }


    
}
