package com.example.poormusic.service.track_service;

import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.dto.TrackRequest;
import com.example.poormusic.entity.Track;

import java.util.Set;

public interface TrackService {
    Set<TrackDto> findAll();
    Set<TrackDto> findAllByPlaylistsId(Long playlistId);
    void saveTrack(Track track);
    void deleteById(Long trackId);

    void addTrackToPlaylist(Long playlistId, Long trackId);

    TrackDto addTrack(TrackRequest trackRequest);
}
