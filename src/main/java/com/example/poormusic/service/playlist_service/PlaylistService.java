package com.example.poormusic.service.playlist_service;

import com.example.poormusic.dto.playlist.PlaylistCreateRequest;
import com.example.poormusic.dto.playlist.PlaylistDto;
import com.example.poormusic.dto.playlist.PlaylistSummaryDto;
import com.example.poormusic.entity.Playlist;
import org.springframework.security.core.Authentication;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.Set;

public interface PlaylistService {

    Set<PlaylistDto> findAllByUserId(Long userId);

    Set<PlaylistSummaryDto> findSummaryByUserId(Long userId);

    void save(Playlist playlist);

    Optional<PlaylistDto> findById(Long playlistId);

    Optional<PlaylistSummaryDto> findSummaryPlaylistById(Long userId, Long playlistId, Authentication authentication);

    PlaylistDto createPlaylist(Long userId, PlaylistCreateRequest request, Authentication authentication) throws AccessDeniedException;

    void deleteById(Long playlistId);
}
