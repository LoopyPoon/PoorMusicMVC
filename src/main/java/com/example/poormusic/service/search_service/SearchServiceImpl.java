package com.example.poormusic.service.search_service;

import com.example.poormusic.dto.search_service_dto.SearchTrackDto;
import com.example.poormusic.entity.Artist;
import com.example.poormusic.entity.Track;
import com.example.poormusic.mapper.SearchTrackMapper;
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
    private final SearchTrackMapper searchTrackMapper;

    @Autowired
    public SearchServiceImpl(TrackRepository trackRepository,
                             TrackMapper trackMapper,
                             SearchTrackMapper searchTrackMapper) {
        this.trackRepository = trackRepository;
        this.trackMapper = trackMapper;
        this.searchTrackMapper = searchTrackMapper;
    }
    @Override
    public Page<SearchTrackDto> searchTrack(String query, Pageable pageable) {
        Page<Track> tracksByTitleContainingIgnoreCase = trackRepository.findTracksByTitleContainingIgnoreCase(query, pageable);
        for (Track track : tracksByTitleContainingIgnoreCase) {
            System.out.println("Track: " + track.getTitle());
            for (Artist artist : track.getArtists()) {
                System.out.println("Artist: " + artist.getTitle());
            }
        }
        return tracksByTitleContainingIgnoreCase.map(searchTrackMapper::toDto);
    }
}
