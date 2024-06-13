package com.example.poormusic.service.playlist_service;

import com.example.poormusic.dto.PlaylistDto;
import com.example.poormusic.entity.Playlist;
import java.util.Optional;
import java.util.Set;

public interface PlaylistService {

    Set<PlaylistDto> findAllByUserId(Long userId);

    void save(Playlist playlist);

    Optional<PlaylistDto> findById(Long playlistId);

    void deleteById(Long playlistId);
}
