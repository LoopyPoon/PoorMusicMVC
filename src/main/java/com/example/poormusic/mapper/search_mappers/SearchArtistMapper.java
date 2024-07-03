package com.example.poormusic.mapper.search_mappers;

import com.example.poormusic.dto.search_service_dto.SearchAlbumDto;
import com.example.poormusic.dto.search_service_dto.SearchArtistDto;
import com.example.poormusic.entity.Album;
import com.example.poormusic.entity.Artist;
import com.example.poormusic.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SearchArtistMapper extends BaseMapper<Artist, SearchArtistDto> {

    @Override
    @Mapping(source = "genres", target = "genres")
    SearchArtistDto toDto(Artist entity);

    @Override
    @Mapping(source = "genres", target = "genres")
    Artist toEntity(SearchArtistDto dto);

    @Override
    @Mapping(source = "genres", target = "genres")
    void updateModel(SearchArtistDto dto, @MappingTarget Artist entity);

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
