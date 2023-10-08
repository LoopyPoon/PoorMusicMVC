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
public class TrackDto extends BaseDto {

    private Long id;

    @NotEmpty
    private String title;

    private List<ArtistDto> artistDtos;

    @NotEmpty
    private double duration;

    private List<PlaylistDto> playlistDtos;

    private AlbumDto albumDto;
}
