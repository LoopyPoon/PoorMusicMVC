package com.example.poormusic.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto extends BaseDto {

    private Long id;

    @NotEmpty
    private String title;

    private List<ArtistDto> artistDtos;

    @NotEmpty
    private int year;

    private List<TrackDto> trackDtos;

    private GenreDto genreDto;
}
