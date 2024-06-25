package com.example.poormusic.service.album_service;

import com.example.poormusic.dto.AddAlbumDto;
import com.example.poormusic.dto.AddTrackDto;
import com.example.poormusic.dto.AlbumDto;
import com.example.poormusic.entity.Album;
import com.example.poormusic.entity.Artist;
import com.example.poormusic.entity.Genre;
import com.example.poormusic.entity.Track;
import com.example.poormusic.mapper.AlbumMapper;
import com.example.poormusic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final ArtistRepository artistRepository;
    private final TrackRepository trackRepository;
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository,
                            UserRepository userRepository,
                            GenreRepository genreRepository,
                            ArtistRepository artistRepository,
                            TrackRepository trackRepository,
                            AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
        this.albumMapper = albumMapper;
    }

    @Override
    public Set<AlbumDto> findAllByUserId(Long userId) {
        List<Album> albums = albumRepository.findAllByUsersId(userId);
        return albums.stream()
                .map(albumMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public void save(Album album) {
        albumRepository.save(album);
    }

    @Override
    public Optional<AlbumDto> findById(Long albumId) {
        Optional<Album> album = albumRepository.findById(albumId);
        return album.map(albumMapper::toDto);
    }

    @Override
    public void deleteById(Long albumId) {
        albumRepository.deleteById(albumId);
    }

    @Override
    @Transactional
    public void addNewAlbum(AddAlbumDto albumDto) {
        Genre genre = genreRepository.findByTitle(albumDto.getGenre())
                .orElseGet(() -> genreRepository.save(new Genre(albumDto.getGenre())));

        Set<Artist> artists = albumDto.getArtists().stream()
                .map(artistTitle -> artistRepository.findByTitle(artistTitle)
                        .orElseGet(() -> artistRepository.save(new Artist(artistTitle))))
                .collect(Collectors.toSet());

        Album album = new Album();
        album.setTitle(albumDto.getTitle());
        album.setYear(albumDto.getYear());
        album.setGenre(genre);
        album.setArtists(artists);
        albumRepository.save(album);

        for (AddTrackDto addTrackDto : albumDto.getTracks()) {
            Set<Artist> trackArtists = addTrackDto.getArtists().stream()
                    .map(artistTitle -> artistRepository.findByTitle(artistTitle)
                            .orElseGet(() -> artistRepository.save(new Artist(artistTitle))))
                    .collect(Collectors.toSet());

            Track track = new Track();
            track.setTitle(addTrackDto.getTitle());
            track.setDuration(addTrackDto.getDuration());
            track.setArtists(trackArtists);
            track.setAlbum(album);
            trackRepository.save(track);
        }
    }
}
