package com.example.poormusic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto extends BaseDto{

    private Long id;

    @NotBlank
    private String title;

    private UserDto user;

    private Set<TrackDto> tracks;

}
