package com.example.poormusic.mapper.search_mappers;

import com.example.poormusic.dto.search_service_dto.SearchAlbumDto;
import com.example.poormusic.dto.search_service_dto.SearchTrackDto;
import com.example.poormusic.entity.Album;
import com.example.poormusic.entity.Track;
import com.example.poormusic.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SearchAlbumMapper extends BaseMapper<Album, SearchAlbumDto> {

    @Override
    @Mapping(source = "artists", target = "artists")
    SearchAlbumDto toDto(Album entity);

    @Override
    @Mapping(source = "artists", target = "artists")
    Album toEntity(SearchAlbumDto dto);

    @Override
    @Mapping(source = "artists", target = "artists")
    void updateModel(SearchAlbumDto dto, @MappingTarget Album entity);

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
