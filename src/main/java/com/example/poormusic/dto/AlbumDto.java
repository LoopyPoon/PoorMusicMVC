package com.example.poormusic.dto;

import com.example.poormusic.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AlbumDto extends BaseDto {

    private Long id;

    @NotBlank
    private String title;

    private int year;

    private Set<ArtistDto> artists;

    private Set<TrackDto> tracks;

    private GenreDto genre;

    private Set<UserDto> users;
}
