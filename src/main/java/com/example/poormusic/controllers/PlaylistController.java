package com.example.poormusic.controllers;

import com.example.poormusic.dto.PlaylistDto;
import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.entity.Playlist;
import com.example.poormusic.entity.User;
import com.example.poormusic.service.playlist_service.PlaylistService;
import com.example.poormusic.service.track_service.TrackService;
import com.example.poormusic.service.user_service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private final PlaylistService playlistService;
    private final UserService userService;
    private final TrackService trackService;

    @Autowired
    public PlaylistController(PlaylistService playlistService,
                              UserService userService,
                              TrackService trackService) {
        this.playlistService = playlistService;
        this.userService = userService;
        this.trackService = trackService;
    }

    @GetMapping("/playlists")
    public ModelAndView getAllPlaylists() {
        log.info("playlist -> connections");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsernameOrEmail(auth.getName(), auth.getName());
        ModelAndView mav = new ModelAndView("playlists");
        Set<PlaylistDto> playlistList = playlistService.findAllByUserId(user.orElseThrow().getId());
        mav.addObject("playlists", playlistList);
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
        ModelAndView mav = new ModelAndView("show-playlist-form");
        Optional<PlaylistDto> optionalPlaylist = playlistService.findById(playlistId);
        PlaylistDto playlist = new PlaylistDto();
        if (optionalPlaylist.isPresent()) {
            playlist = optionalPlaylist.get();
        }
        Set<TrackDto> trackDtos = trackService.findAllByPlaylistsId(playlistId);
        mav.addObject("playlist", playlist);
        mav.addObject("tracks", trackDtos);
        return mav;
    }

    @GetMapping("/deletePlaylist")
    public String deletePlaylist(@RequestParam Long playlistId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsernameOrEmail(auth.getName(), auth.getName());

        user.orElseThrow().deletePlaylist(playlistId);
        playlistService.deleteById(playlistId);
        return "redirect:/playlists";
    }

    @PostMapping("/showUpdateForm")
    public String addTrackToPlaylist(@RequestParam Long playlistId, @RequestParam Long trackId, Model model) {
        trackService.addTrackToPlaylist(playlistId, trackId);
        Optional<PlaylistDto> optionalPlaylist = playlistService.findById(playlistId);
        PlaylistDto playlist = new PlaylistDto();
        if (optionalPlaylist.isPresent()) {
            playlist = optionalPlaylist.get();
        }
        Set<TrackDto> trackDtos = trackService.findAllByPlaylistsId(playlistId);
        model.addAttribute("successMessage", "Track added to playlist successfully");
        model.addAttribute("tracks", trackDtos);
        model.addAttribute("playlist", playlist);
        return "show-playlist-form";
    }

//    @Deprecated
//    @GetMapping("/playlistsOld")
//    public ModelAndView getAllPlaylists2() {
//        ModelAndView mav = new ModelAndView("playlists");
//        mav.addObject("playlistsOld", playlistRepository.findAll());
//        return mav;
//    }

}
