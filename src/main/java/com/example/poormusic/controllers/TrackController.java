package com.example.poormusic.controllers;

import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.dto.TrackRequest;
import com.example.poormusic.entity.Track;
import com.example.poormusic.entity.User;
import com.example.poormusic.mapper.TrackMapper;
import com.example.poormusic.service.track_service.TrackService;
import com.example.poormusic.service.user_service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
//@RequestMapping("/users")
public class TrackController {
    private final TrackService trackService;
    private final UserService userService;

    @Autowired
    public TrackController(TrackService trackService,
                           UserService userService) {
        this.trackService = trackService;
        this.userService = userService;
    }

    @GetMapping("/tracks")
    public ModelAndView getAllTracks() {
        log.info("tracks -> connections");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsernameOrEmail(auth.getName(), auth.getName());
        ModelAndView mav = new ModelAndView("tracks");
        Set<TrackDto> trackDtos = trackService.findAll();
        mav.addObject("tracks", trackDtos);
        return mav;
    }

    @GetMapping("/addTrackForm")
    public ModelAndView addTrackForm() {
        ModelAndView mav = new ModelAndView("add-track-form");
        Track track = new Track();
        mav.addObject("track", track);
        return mav;
    }

    @PostMapping("/saveTrack")
    public String saveTrack(@ModelAttribute Track track) {
        trackService.saveTrack(track);
        return "redirect:/tracks";
    }

    @PostMapping("/addTracks")
    public ResponseEntity<String> addTrack(@RequestBody @Valid TrackRequest trackRequest) {
        trackService.addTrack(trackRequest);
        return ResponseEntity.ok("Track add successfully");
    }
}
