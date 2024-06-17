package com.example.poormusic.controllers;

import com.example.poormusic.entity.Track;
import com.example.poormusic.mapper.TrackMapper;
import com.example.poormusic.service.track_service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class TrackController {
    private TrackService trackService;
    private TrackMapper trackMapper;

    @Autowired
    public TrackController(TrackService trackService,
                           TrackMapper trackMapper) {
        this.trackService = trackService;
        this.trackMapper = trackMapper;
    }

}
