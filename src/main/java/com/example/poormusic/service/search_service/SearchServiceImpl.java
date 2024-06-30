package com.example.poormusic.service.search_service;

import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.entity.Track;
import com.example.poormusic.mapper.TrackMapper;
import com.example.poormusic.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    private final TrackRepository trackRepository;
    private final TrackMapper trackMapper;

    @Autowired
    public SearchServiceImpl(TrackRepository trackRepository,
                             TrackMapper trackMapper) {
        this.trackRepository = trackRepository;
        this.trackMapper = trackMapper;
    }
    @Override
    public Page<TrackDto> searchTrack(String query, Pageable pageable) {
        Page<Track> tracksByTitleContainingIgnoreCase = trackRepository.findTracksByTitleContainingIgnoreCase(query, pageable);
        return tracksByTitleContainingIgnoreCase.map(trackMapper::toDto);
    }
}
