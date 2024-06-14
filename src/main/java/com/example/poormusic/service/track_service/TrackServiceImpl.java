package com.example.poormusic.service.track_service;

import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.entity.Artist;
import com.example.poormusic.entity.Track;
import com.example.poormusic.mapper.PlaylistMapper;
import com.example.poormusic.mapper.TrackMapper;
import com.example.poormusic.repository.AlbumRepository;
import com.example.poormusic.repository.ArtistRepository;
import com.example.poormusic.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final TrackMapper trackMapper;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository,
                            ArtistRepository artistRepository,
                            AlbumRepository albumRepository,
                            TrackMapper trackMapper) {
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.trackMapper = trackMapper;
    }


    @Override
    public Set<TrackDto> findAllByPlaylistsId(Long playlistId) {
        Set<Track> tracks = trackRepository.findAllByPlaylistsId(playlistId);
        return tracks.stream()
                .map(trackMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public void saveTrack(Track track) {
        artistRepository.saveAll(track.getArtists());
        albumRepository.save(track.getAlbum());
        trackRepository.save(track)
    }

    @Override
    public void deleteById(Long trackId) {
        trackRepository.deleteById(trackId);
    }
}
