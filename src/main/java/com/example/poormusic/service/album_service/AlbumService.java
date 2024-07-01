package com.example.poormusic.service.album_service;

import com.example.poormusic.dto.create_new_album_dto.AddAlbumDto;
import com.example.poormusic.dto.AlbumDto;
import com.example.poormusic.entity.Album;

import java.util.Optional;
import java.util.Set;

public interface AlbumService {

    Set<AlbumDto> findAllByUserId(Long userId);

    void save(Album album);

    Optional<AlbumDto> findById(Long albumId);

    void deleteById(Long albumId);

    void addNewAlbum(AddAlbumDto album);
}
