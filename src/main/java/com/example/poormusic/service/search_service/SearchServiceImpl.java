package com.example.poormusic.service.search_service;

import com.example.poormusic.dto.search_service_dto.SearchAlbumDto;
import com.example.poormusic.dto.search_service_dto.SearchArtistDto;
import com.example.poormusic.dto.search_service_dto.SearchTrackDto;
import com.example.poormusic.entity.Album;
import com.example.poormusic.entity.Artist;
import com.example.poormusic.entity.Track;
import com.example.poormusic.mapper.search_mappers.SearchAlbumMapper;
import com.example.poormusic.mapper.search_mappers.SearchArtistMapper;
import com.example.poormusic.mapper.search_mappers.SearchTrackMapper;
import com.example.poormusic.repository.AlbumRepository;
import com.example.poormusic.repository.ArtistRepository;
import com.example.poormusic.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    private final TrackRepository trackRepository;
    private final SearchTrackMapper searchTrackMapper;
    private final AlbumRepository albumRepository;
    private final SearchAlbumMapper searchAlbumMapper;
    private final ArtistRepository artistRepository;
    private final SearchArtistMapper searchArtistMapper;

    @Autowired
    public SearchServiceImpl(TrackRepository trackRepository,
                             SearchTrackMapper searchTrackMapper,
                             AlbumRepository albumRepository,
                             SearchAlbumMapper searchAlbumMapper,
                             ArtistRepository artistRepository,
                             SearchArtistMapper searchArtistMapper) {
        this.trackRepository = trackRepository;
        this.searchTrackMapper = searchTrackMapper;
        this.albumRepository = albumRepository;
        this.searchAlbumMapper = searchAlbumMapper;
        this.artistRepository = artistRepository;
        this.searchArtistMapper = searchArtistMapper;
    }
    @Override
    public Page<SearchTrackDto> searchTrack(String query, Pageable pageable) {
        Page<Track> tracksByTitleContainingIgnoreCase = trackRepository.findTracksByTitleContainingIgnoreCase(query, pageable);
        return tracksByTitleContainingIgnoreCase.map(searchTrackMapper::toDto);
    }

    @Override
    public Page<SearchAlbumDto> searchAlbum(String query, Pageable pageable) {
        Page<Album> albumsByTitleContainingIgnoreCase= albumRepository.findAlbumsByTitleContainingIgnoreCase(query, pageable);
        return albumsByTitleContainingIgnoreCase.map(searchAlbumMapper::toDto);
    }

    @Override
    public Page<SearchArtistDto> searchArtist(String query, Pageable pageable) {
        Page<Artist> artistsByTitleContainingIgnoreCase = artistRepository.findArtistsByTitleContainingIgnoreCase(query, pageable);
        return artistsByTitleContainingIgnoreCase.map(searchArtistMapper::toDto);
    }
}
