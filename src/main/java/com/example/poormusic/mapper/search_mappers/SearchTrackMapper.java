package com.example.poormusic.mapper.search_mappers;

import com.example.poormusic.dto.create_new_album_dto.AddTrackDto;
import com.example.poormusic.dto.search_service_dto.SearchTrackDto;
import com.example.poormusic.entity.Artist;
import com.example.poormusic.entity.Track;
import com.example.poormusic.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SearchTrackMapper extends BaseMapper<Track, SearchTrackDto> {

    @Override
    @Mapping(source = "artists", target = "artists")
    @Mapping(source = "album", target = "album")
    SearchTrackDto toDto(Track entity);

    @Override
    @Mapping(source = "artists", target = "artists")
    @Mapping(source = "album", target = "album")
    Track toEntity(SearchTrackDto dto);

    @Override
    @Mapping(source = "artists", target = "artists")
    @Mapping(source = "album", target = "album")
    void updateModel(SearchTrackDto dto, @MappingTarget Track entity);

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
