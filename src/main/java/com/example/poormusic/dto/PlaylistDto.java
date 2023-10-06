package com.example.poormusic.dto;

import com.example.poormusic.entity.Track;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private List<Track> tracks;

}
