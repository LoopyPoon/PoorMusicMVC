package com.example.poormusic.service.album_service;

import com.example.poormusic.dto.create_new_album_dto.AddAlbumDto;
import com.example.poormusic.dto.create_new_album_dto.AddTrackDto;
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

//        Set<Artist> artists = albumDto.getArtists().stream()
//                .map(artistTitle -> artistRepository.findByTitle(artistTitle)
//                        .orElseGet(() -> artistRepository.save(new Artist(artistTitle))))
//                .collect(Collectors.toSet());

        Set<Artist> artists = albumDto.getArtists().stream()
                .map(artistTitle -> artistRepository.findByTitle(artistTitle)
                        .orElseGet(() -> {
                            Artist newArtist = new Artist(artistTitle);
                            newArtist.getGenres().add(genre);
                            return artistRepository.save(newArtist); // Сохраняем артиста с жанром
                        }))
                .collect(Collectors.toSet());

        // Если у существующих артистов жанр еще не добавлен, добавляем его
        artists.forEach(artist -> {
            if (!artist.getGenres().contains(genre)) {
                artist.getGenres().add(genre);
                artistRepository.save(artist);
            }
        });

        Album album = new Album();
        album.setTitle(albumDto.getTitle());
        album.setYear(albumDto.getYear());
        album.setGenre(genre);
        album.setArtists(artists);
        albumRepository.save(album);

//        for (AddTrackDto addTrackDto : albumDto.getTracks()) {
//            Set<Artist> trackArtists = addTrackDto.getArtists().stream()
//                    .map(artistTitle -> artistRepository.findByTitle(artistTitle)
//                            .orElseGet(() -> artistRepository.save(new Artist(artistTitle))))
//                    .collect(Collectors.toSet());

        for (AddTrackDto addTrackDto : albumDto.getTracks()) {
            Set<Artist> trackArtists = addTrackDto.getArtists().stream()
                    .map(artistTitle -> artistRepository.findByTitle(artistTitle)
                            .orElseGet(() -> {
                                Artist newArtist = new Artist(artistTitle);
                                artistRepository.save(newArtist); // Сначала сохраняем артиста
                                newArtist.getGenres().add(genre);
                                return artistRepository.save(newArtist); // Сохраняем артиста с жанром
                            }))
                    .collect(Collectors.toSet());

            // Если у существующих артистов жанр еще не добавлен, добавляем его
            trackArtists.forEach(artist -> {
                if (!artist.getGenres().contains(genre)) {
                    artist.getGenres().add(genre);
                    artistRepository.save(artist);
                }
            });

            Track track = new Track();
            track.setTitle(addTrackDto.getTitle());
            track.setDuration(addTrackDto.getDuration());
            track.setArtists(trackArtists);
            track.setAlbum(album);
            trackRepository.save(track);
        }
    }
}
