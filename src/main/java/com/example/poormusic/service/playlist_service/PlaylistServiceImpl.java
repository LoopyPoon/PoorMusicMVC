package com.example.poormusic.service.playlist_service;

import com.example.poormusic.dto.playlist.PlaylistCreateRequest;
import com.example.poormusic.dto.playlist.PlaylistDto;
import com.example.poormusic.dto.playlist.PlaylistSummaryDto;
import com.example.poormusic.entity.Playlist;
import com.example.poormusic.entity.User;
import com.example.poormusic.exceptions.ResourceNotFoundException;
import com.example.poormusic.mapper.playlist.PlaylistMapper;
import com.example.poormusic.repository.PlaylistRepository;
import com.example.poormusic.repository.UserRepository;
import com.example.poormusic.service.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final PlaylistMapper playlistMapper;
    private final UserService userService;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository,
                               UserRepository userRepository,
                               PlaylistMapper playlistMapper, UserService userService) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.playlistMapper = playlistMapper;
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PlaylistDto> findAllByUserId(Long userId) {
        List<Playlist> playlists = playlistRepository.findAllByUserId(userId);
        return playlists.stream()
                .map(playlistMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PlaylistSummaryDto> findSummaryByUserId(Long userId) {


        List<Playlist> playlists = playlistRepository.findAllByUserId(userId);
        return playlists.stream()
                .map(playlistMapper::toSummaryDto)
                .collect(Collectors.toSet());
    }

    @Override
    public void save(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public Optional<PlaylistDto> findById(Long playlistId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        return playlist.map(playlistMapper::toDto);
    }

    @Transactional
    @Override
    public PlaylistDto createPlaylist(Long userId, PlaylistCreateRequest request, Authentication authentication) throws AccessDeniedException {
        User currentUser = userService.getAuthenticatedUser(authentication);

        User requesteduser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User " + userId + " not found"));

        if (!currentUser.getId().equals(userId)) {
            throw new AccessDeniedException("You are not allowed to create a playlist for another user");
        }
        
        Playlist playlist = new Playlist();
        playlist.setTitle(request.getTitle());
        playlist.setKind(request.getKind());
        playlist.setUser(requesteduser);

        playlist = playlistRepository.save(playlist);

        return playlistMapper.toDto(playlist);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PlaylistSummaryDto> findSummaryPlaylistById(Long userId, Long playlistId, Authentication authentication) {
//        User currentUser = userService.getAuthentication(authentication);
        User requestedUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User " + userId + " not found"));

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist " + playlistId + " not found"));

        if (!playlist.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Playlist " + playlistId + " does not belong to user " + userId);
        }

//        boolean isOwner = currentUser.getId().equals(userId);
//        boolean isAdmin = currentUser.getRoles().contains(Role.ADMIN);
//
//        if (!isOwner && !isAdmin && "private".equals(playlist.getKind())) {
//            throw new AccessDeniedException("Нет доступа к плейлисту с ID " + playlistId);
//        }

        return Optional.of(playlistMapper.toSummaryDto(playlist));
    }

    @Override
    public void deleteById(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }
}
