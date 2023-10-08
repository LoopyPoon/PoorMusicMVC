package com.example.poormusic.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto extends BaseDto{

    @NotEmpty
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private UserDto userDto;

    @NotEmpty
    private List<TrackDto> trackDtos;

}
