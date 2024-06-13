package com.example.poormusic.service.album_service;

import com.example.poormusic.dto.AlbumDto;
import com.example.poormusic.entity.Album;
import com.example.poormusic.mapper.AlbumMapper;
import com.example.poormusic.repository.AlbumRepository;
import com.example.poormusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository,
                            UserRepository userRepository,
                            AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.albumMapper = albumMapper;
    }

    @Override
    public Set<AlbumDto> findAllByUserId(Long userId) {
        List<Album> albums = albumRepository.findAllByUserId(userId);
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
}
