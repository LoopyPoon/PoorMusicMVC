package com.example.poormusic.controllers;

import com.example.poormusic.dto.create_new_album_dto.AddTrackDto;
import com.example.poormusic.dto.search_service_dto.SearchAlbumDto;
import com.example.poormusic.dto.search_service_dto.SearchArtistDto;
import com.example.poormusic.dto.search_service_dto.SearchTrackDto;
import com.example.poormusic.service.search_service.SearchService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "query", required = false) String query,
                               @RequestParam(defaultValue = "0") @Min(0) Integer page,
                               @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size,
                               @RequestParam(defaultValue = "track") String type) {
        ModelAndView mav = new ModelAndView("search");

        if (type.equals("album")) {
            Page<SearchAlbumDto> albumDtoPage = searchService.searchAlbum(query, PageRequest.of(page, size));
            mav.addObject("albumPage", albumDtoPage);
            int totalPages = albumDtoPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                mav.addObject("pageNumbers", pageNumbers);
            }
        } else if (type.equals("artist")) {
            Page<SearchArtistDto> artistDtoPage = searchService.searchArtist(query, PageRequest.of(page, size));
            mav.addObject("artistPage", artistDtoPage);
            int totalPages = artistDtoPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                mav.addObject("pageNumbers", pageNumbers);
            }
        } else {
            Page<SearchTrackDto> trackDtoPage = searchService.searchTrack(query, PageRequest.of(page, size));
            mav.addObject("trackPage", trackDtoPage);
            int totalPages = trackDtoPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                mav.addObject("pageNumbers", pageNumbers);
            }
        }

        mav.addObject("query", query);
        mav.addObject("type", type);

        return mav;
    }

    @GetMapping("/searchTrack")
    public ModelAndView searchTrack(@RequestParam(value = "query", required = false) String query,
                              @RequestParam(defaultValue = "0") @Min(0) Integer page,
                              @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        ModelAndView mav = new ModelAndView("search");

        Page<SearchTrackDto> trackDtoPage = searchService.searchTrack(query, PageRequest.of(page,size));
        Page<SearchAlbumDto> albumDtoPage = searchService.searchAlbum(query, PageRequest.of(page, size));
        Page<SearchArtistDto> artistDtoPage = searchService.searchArtist(query, PageRequest.of(page, size));

        mav.addObject("trackPage", trackDtoPage);
        mav.addObject("albumPage", albumDtoPage);
        mav.addObject("artistPage", artistDtoPage);

        int totalPages = trackDtoPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            mav.addObject("pageNumbers", pageNumbers);
        }

        return mav;
    }

    @GetMapping("/searchAlbum")
    public ModelAndView searchAlbum(@RequestParam(value = "query", required = false) String query,
                                    @RequestParam(defaultValue = "0") @Min(0) Integer page,
                                    @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        ModelAndView mav = new ModelAndView("search");

        Page<SearchTrackDto> trackDtoPage = searchService.searchTrack(query, PageRequest.of(page, size));
        Page<SearchAlbumDto> albumDtoPage = searchService.searchAlbum(query, PageRequest.of(page, size));
        Page<SearchArtistDto> artistDtoPage = searchService.searchArtist(query, PageRequest.of(page, size));

        mav.addObject("trackPage", trackDtoPage);
        mav.addObject("albumPage", albumDtoPage);
        mav.addObject("artistPage", artistDtoPage);

        int totalPages = albumDtoPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            mav.addObject("pageNumbers", pageNumbers);
        }

        return mav;
    }

    @GetMapping("/searchArtist")
    public ModelAndView searchArtist(@RequestParam(value = "query", required = false) String query,
                                    @RequestParam(defaultValue = "0") @Min(0) Integer page,
                                    @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        ModelAndView mav = new ModelAndView("search");

        Page<SearchTrackDto> trackDtoPage = searchService.searchTrack(query, PageRequest.of(page, size));
        Page<SearchAlbumDto> albumDtoPage = searchService.searchAlbum(query, PageRequest.of(page, size));
        Page<SearchArtistDto> artistDtoPage = searchService.searchArtist(query, PageRequest.of(page, size));

        mav.addObject("trackPage", trackDtoPage);
        mav.addObject("albumPage", albumDtoPage);
        mav.addObject("artistPage", artistDtoPage);

        int totalPages = artistDtoPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            mav.addObject("pageNumbers", pageNumbers);
        }

        return mav;
    }
}
