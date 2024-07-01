package com.example.poormusic.dto.create_new_album_dto;

import com.example.poormusic.dto.BaseDto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddAlbumDto extends BaseDto {
    @NonNull
    private String title;
    @NonNull
    private int year;
    @NonNull
    private String genre;
    @NonNull
    private List<String> artists;
    @NonNull
    private List<AddTrackDto> tracks;
}
