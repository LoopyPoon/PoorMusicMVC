package com.example.poormusic.service.search_service;

import com.example.poormusic.dto.create_new_album_dto.AddTrackDto;
import com.example.poormusic.dto.search_service_dto.SearchAlbumDto;
import com.example.poormusic.dto.search_service_dto.SearchArtistDto;
import com.example.poormusic.dto.search_service_dto.SearchTrackDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface SearchService {

    Page<SearchTrackDto> searchTrack(String query, Pageable pageable);

    Page<SearchAlbumDto> searchAlbum(String query, Pageable pageable);

    Page<SearchArtistDto> searchArtist(String query, Pageable pageable);
}
