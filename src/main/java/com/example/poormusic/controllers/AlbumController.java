package com.example.poormusic.controllers;

import com.example.poormusic.dto.AlbumDto;
import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.dto.TrackRequest;
import com.example.poormusic.entity.User;
import com.example.poormusic.service.album_service.AlbumService;
import com.example.poormusic.service.track_service.TrackService;
import com.example.poormusic.service.user_service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final TrackService trackService;

    @Autowired
    public AlbumController(AlbumService albumService,
                           UserService userService,
                           TrackService trackService) {
        this.albumService = albumService;
        this.userService = userService;
        this.trackService = trackService;
    }

    @GetMapping("/albums")
    public ModelAndView getAllAlbums() {
        log.info("album -> connections");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsernameOrEmail(auth.getName(), auth.getName());
        ModelAndView mav = new ModelAndView("albums");
        Set<AlbumDto> albumDtoSet = albumService.findAllByUserId(user.orElseThrow().getId());
        mav.addObject("albums", albumDtoSet);
        return mav;
    }

    @GetMapping("/showUpdateAlbumForm")
    public ModelAndView showUpdateForm(@RequestParam Long albumId) {
        ModelAndView mav = new ModelAndView("show-album-form");
        Optional<AlbumDto> albumDtoOptional = albumService.findById(albumId);
        AlbumDto albumDto = new AlbumDto();
        if (albumDtoOptional.isPresent()) {
            albumDto = albumDtoOptional.get();
        }
        Set<TrackDto> trackDtos = trackService.findAllByAlbumId(albumId);
        mav.addObject("album", albumDto);
        return mav;
    }

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
