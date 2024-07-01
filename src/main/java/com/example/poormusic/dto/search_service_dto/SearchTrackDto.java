package com.example.poormusic.dto.search_service_dto;

import com.example.poormusic.dto.BaseDto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchTrackDto extends BaseDto {

    private Long id;
    @NonNull
    private String title;
    @NonNull
    private List<SearchArtistDto> artists;
    @NonNull
    private double duration;
    @NonNull
    private SearchAlbumDto album;
}