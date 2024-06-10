package com.example.poormusic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto extends BaseDto {

    private Long id;

    @NotBlank
    private String title;

    private Set<ArtistDto> artists;

    private Set<AlbumDto> albums;
}
