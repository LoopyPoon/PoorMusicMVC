package com.example.poormusic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto extends BaseDto {

    private Long id;

    @NotBlank
    private String title;

    private Set<ArtistDto> artists;

    private int year;

    private Set<TrackDto> tracks;

    private GenreDto genre;
}
