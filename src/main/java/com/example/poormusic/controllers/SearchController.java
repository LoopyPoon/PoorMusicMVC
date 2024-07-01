package com.example.poormusic.controllers;

import com.example.poormusic.dto.create_new_album_dto.AddTrackDto;
import com.example.poormusic.dto.search_service_dto.SearchTrackDto;
import com.example.poormusic.service.search_service.SearchService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/searchTrack")
    public ModelAndView searchTrack(@RequestParam(value = "query", required = false) String query,
                              @RequestParam(defaultValue = "0") @Min(0) Integer page,
                              @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        ModelAndView mav = new ModelAndView("search");

        Page<SearchTrackDto> trackDtoPage = searchService.searchTrack(query, PageRequest.of(page,size));

        mav.addObject("trackPage", trackDtoPage);
        mav.addObject("currentPage", page);
        return mav;
    }
}
