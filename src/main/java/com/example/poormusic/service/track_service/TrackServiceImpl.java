package com.example.poormusic.service.track_service;

import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.dto.TrackRequest;
import com.example.poormusic.entity.Album;
import com.example.poormusic.entity.Artist;
import com.example.poormusic.entity.Genre;
import com.example.poormusic.entity.Track;
import com.example.poormusic.mapper.PlaylistMapper;
import com.example.poormusic.mapper.TrackMapper;
import com.example.poormusic.repository.AlbumRepository;
import com.example.poormusic.repository.ArtistRepository;
import com.example.poormusic.repository.GenreRepository;
import com.example.poormusic.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;
    private final TrackMapper trackMapper;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository,
                            ArtistRepository artistRepository,
                            AlbumRepository albumRepository,
                            GenreRepository genreRepository,
                            TrackMapper trackMapper) {
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
        this.trackMapper = trackMapper;
    }


    @Override
    public Set<TrackDto> findAll() {
        List<Track> tracks = trackRepository.findAll();
        return tracks.stream()
                .map(trackMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TrackDto> findAllByPlaylistsId(Long playlistId) {
        Set<Track> tracks = trackRepository.findAllByPlaylistsId(playlistId);
        return tracks.stream()
                .map(trackMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void saveTrack(Track track) {
        artistRepository.saveAll(track.getArtists());
        albumRepository.save(track.getAlbum());
        trackRepository.save(track);
    }

    @Override
    public void deleteById(Long trackId) {
        trackRepository.deleteById(trackId);
    }

    @Override
    public void addTrackToPlaylist(Long playlistId, Long trackId) {

    }

    @Override
    @Transactional
    public TrackDto addTrack(TrackRequest trackRequest) {
        Genre genre = genreRepository.findByTitle(trackRequest.getAlbumGenre())
                .orElse(new Genre(trackRequest.getAlbumGenre()));
        genreRepository.save(genre);

        Album album = albumRepository.findByTitle(trackRequest.getAlbumTitle())
                .orElse(new Album(trackRequest.getAlbumTitle(), trackRequest.getAlbumYear(), genre));
        albumRepository.save(album);

        Set<Artist> artists = trackRequest.getArtists().stream()
                .map(artistTitle -> artistRepository.findByTitle(artistTitle)
                        .orElse(new Artist(artistTitle)))
                .collect(Collectors.toSet());
        artistRepository.saveAll(artists);

        Track track = new Track();
        track.setTitle(trackRequest.getTitle());
        track.setDuration(trackRequest.getDuration());
        track.setAlbum(album);
        track.setArtists(artists);
        trackRepository.save(track);

        return trackMapper.toDto(track);
    }
}
