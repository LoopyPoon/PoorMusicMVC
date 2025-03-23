package com.example.poormusic.controllers;

import com.example.poormusic.dto.playlist.PlaylistCreateRequest;
import com.example.poormusic.dto.playlist.PlaylistDto;
import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.dto.playlist.PlaylistSummaryDto;
import com.example.poormusic.entity.Playlist;
import com.example.poormusic.entity.User;
import com.example.poormusic.service.playlist_service.PlaylistService;
import com.example.poormusic.service.track_service.TrackService;
import com.example.poormusic.service.user_service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/playlists")
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

    // Получаем JSON со всеми плейлистами пользователя
    @Operation(summary = "Get a list of playlists for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of playlists found"),
            @ApiResponse(responseCode = "204", description = "No content, No playlists available"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/list")
    public ResponseEntity<Set<PlaylistSummaryDto>> getPlaylists(@PathVariable Long userId, Authentication authentication) {
        log.info("Fetching playlists for user {}", userId);

//        Optional<User> user = userService.findByUsernameOrEmail(authentication.getName(), authentication.getName());
        Optional<User> user = userService.findUserById(userId);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Set<PlaylistSummaryDto> playlistDtos = playlistService.findSummaryByUserId(user.get().getId());

        if (playlistDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(playlistDtos);
    }

    // Получаем JSON с плейлистом пользователя
    @Operation(summary = "Get a user playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Playlist found"),
            @ApiResponse(responseCode = "404", description = "User or playlist not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{playlistId}")
    public ResponseEntity<PlaylistSummaryDto> getPlaylist(
            @PathVariable Long userId,
            @PathVariable Long playlistId,
            Authentication authentication) {
        log.info("Fetching playlist with id {} for user {}", playlistId, userId);

        Optional<User> user = userService.findUserById(userId);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // User not found
        }

        Optional<PlaylistSummaryDto> playlist = playlistService.findSummaryPlaylistById(userId, playlistId, authentication);

        return playlist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Playlist not found
    }

    @Operation(summary = "Create a new playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Playlist created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/create")
    public ResponseEntity<PlaylistDto> createPlaylist(@PathVariable Long userId,
                                                      @Valid @RequestBody PlaylistCreateRequest request,
                                                      Authentication authentication) throws AccessDeniedException {

        log.info("User is creating a new playlist {}", userId);

        PlaylistDto playlistDto = playlistService.createPlaylist(userId, request, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistDto);
    }


    // Получаем Thymeleaf шаблон с плейлистами пользователя
//    @GetMapping("/playlists")
//    public ModelAndView getAllPlaylists() {
//        log.info("playlist -> connections");
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Optional<User> user = userService.findByUsernameOrEmail(auth.getName(), auth.getName());
//        ModelAndView mav = new ModelAndView("playlists");
//        Set<PlaylistDto> playlistList = playlistService.findAllByUserId(user.orElseThrow().getId());
//        mav.addObject("playlists", playlistList);
//        return mav;
//    }

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
