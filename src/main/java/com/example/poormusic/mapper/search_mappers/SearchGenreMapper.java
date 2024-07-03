package com.example.poormusic.mapper.search_mappers;

import com.example.poormusic.dto.search_service_dto.SearchGenreDto;
import com.example.poormusic.dto.search_service_dto.SearchTrackDto;
import com.example.poormusic.entity.Genre;
import com.example.poormusic.entity.Track;
import com.example.poormusic.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SearchGenreMapper extends BaseMapper<Genre, SearchGenreDto> {

    @Override
    SearchGenreDto toDto(Genre entity);

    @Override
    Genre toEntity(SearchGenreDto dto);

    @Override
    void updateModel(SearchGenreDto dto, @MappingTarget Genre entity);

//    @Named("mapArtists")
//    default List<String> mapArtists(Set<Artist> artists) {
//        return artists.stream().map(Artist::getTitle).collect(Collectors.toList());
//    }
//
//    @Named("mapArtistNames")
//    default Set<Artist> mapArtistNames(List<String> artists) {
//        return artists.stream().map(Artist::new).collect(Collectors.toSet());
//    }
}
