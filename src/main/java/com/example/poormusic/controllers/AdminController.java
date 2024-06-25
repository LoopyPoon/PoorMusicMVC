package com.example.poormusic.controllers;

import com.example.poormusic.dto.AddAlbumDto;
import com.example.poormusic.dto.AddTrackDto;
import com.example.poormusic.dto.TrackRequest;
import com.example.poormusic.entity.Album;
import com.example.poormusic.entity.Track;
import com.example.poormusic.entity.User;
import com.example.poormusic.service.album_service.AlbumService;
import com.example.poormusic.service.track_service.TrackService;
import com.example.poormusic.service.user_service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final TrackService trackService;
    private final AlbumService albumService;

    public AdminController(UserService userService,
                           TrackService trackService,
                           AlbumService albumService) {
        this.userService = userService;
        this.trackService = trackService;
        this.albumService = albumService;
    }

    @GetMapping("/users")
    public String showUsersList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/addAlbumForm")
    public ModelAndView addTrackForm() {
        ModelAndView mav = new ModelAndView("add-album-form");
        AddAlbumDto album = new AddAlbumDto();
        mav.addObject("album", album);
        return mav;
    }

    @PostMapping("/saveAlbum")
    public String saveTrack(@ModelAttribute @Valid AddAlbumDto album, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "albums";
        }
        albumService.addNewAlbum(album);
        return "redirect:/admin/addAlbumForm";
    }

    @PostMapping("/addTracks")
    public ResponseEntity<String> addTrack(@RequestBody @Valid TrackRequest trackRequest) {
        trackService.addTrack(trackRequest);
        return ResponseEntity.ok("Track add successfully");
    }

//    @GetMapping("/addTrackForm")
//    public ModelAndView addTrackForm() {
//        ModelAndView mav = new ModelAndView("add-track-form");
//
//    }
}
