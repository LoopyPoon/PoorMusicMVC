package com.example.poormusic.dto.playlist;

import com.example.poormusic.dto.BaseDto;
import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PlaylistDto extends BaseDto {

    private Long id;

    @NotBlank
    private String title;

    private UserDto user;

    private Set<TrackDto> tracks;

}
