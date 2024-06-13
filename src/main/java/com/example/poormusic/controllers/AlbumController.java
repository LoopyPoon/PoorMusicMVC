package com.example.poormusic.controllers;

import com.example.poormusic.dto.AlbumDto;
import com.example.poormusic.entity.User;
import com.example.poormusic.service.album_service.AlbumService;
import com.example.poormusic.service.user_service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
//@RequestMapping("/users")
public class AlbumController {

    private final AlbumService albumService;
    private final UserService userService;

    public AlbumController(AlbumService albumService,
                           UserService userService) {
        this.albumService = albumService;
        this.userService = userService;
    }

    @GetMapping("/albums")
    public ModelAndView getAllAlbums() {
        log.info("album -> connections");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsernameOrEmail(auth.getName(), auth.getName());
        ModelAndView mav = new ModelAndView("albums");
        Set<AlbumDto> albumDtoSet = albumService.findAllByUserId(user.orElseThrow().getId());
        mav.addObject("albums", albumDtoSet);
        mav.addObject("user", user);
        return mav;
    }

//    @GetMapping("/showUpdateForm")
//    public ModelAndView showUpdateForm(@RequestParam Long playlistId) {
//        ModelAndView mav = new ModelAndView("add-playlist-form");
//        Optional<PlaylistDto> optionalPlaylist = playlistService.findById(playlistId);
//        PlaylistDto playlist = new PlaylistDto();
//        if (optionalPlaylist.isPresent()) {
//            playlist = optionalPlaylist.get();
//        }
//        mav.addObject("playlist", playlist);
//        return mav;
//    }

    @GetMapping("/deleteAlbum")
    public String deleteAlbum(@RequestParam Long albumId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsernameOrEmail(auth.getName(), auth.getName());

        user.orElseThrow().deleteAlbum(albumId);
        return "redirect:/albums";
    }

//    @Deprecated
//    @GetMapping("/playlistsOld")
//    public ModelAndView getAllPlaylists2() {
//        ModelAndView mav = new ModelAndView("playlists");
//        mav.addObject("playlistsOld", playlistRepository.findAll());
//        return mav;
//    }


    
}
