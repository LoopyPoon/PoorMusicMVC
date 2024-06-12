package com.example.poormusic.service;

import com.example.poormusic.dto.PlaylistDto;
import com.example.poormusic.entity.Playlist;
import com.example.poormusic.mapper.PlaylistMapper;
import com.example.poormusic.repository.PlaylistRepository;
import com.example.poormusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final PlaylistMapper playlistMapper;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository,
                               UserRepository userRepository,
                               PlaylistMapper playlistMapper) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.playlistMapper = playlistMapper;
    }

    @Override
    public Set<PlaylistDto> findAllByUserId(Long user_id) {
        List<Playlist> playlists = playlistRepository.findAllByUserId(user_id);
        return playlists.stream()
                .map(playlistMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public void save(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public Optional<PlaylistDto> findById(Long playlist_id) {
        Optional<Playlist> playlist = playlistRepository.findById(playlist_id);
        return playlist.map(playlistMapper::toDto);
    }
}
