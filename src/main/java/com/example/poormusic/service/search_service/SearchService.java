package com.example.poormusic.service.search_service;

import com.example.poormusic.dto.TrackDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SearchService {

    Page<TrackDto> searchTrack(String name, Pageable pageable);

}
