package com.example.poormusic.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddTrackDto extends BaseDto {
    @NonNull
    private String title;
    @NonNull
    private List<String> artists;
    @NonNull
    private double duration;
}
