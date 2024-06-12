package com.example.poormusic.service;

import com.example.poormusic.dto.PlaylistDto;
import com.example.poormusic.entity.Playlist;
import java.util.Optional;
import java.util.Set;

public interface PlaylistService {

    Set<PlaylistDto> findAllByUserId(Long user_id);

    void save(Playlist playlist);

    Optional<PlaylistDto> findById(Long playlist_id);
}
