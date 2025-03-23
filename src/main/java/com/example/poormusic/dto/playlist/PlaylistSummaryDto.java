package com.example.poormusic.dto.playlist;

import com.example.poormusic.dto.BaseDto;
import com.example.poormusic.dto.TrackDto;
import com.example.poormusic.dto.user.UserSummaryDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class PlaylistSummaryDto extends BaseDto {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = 20, message = "Title must be between 1 and 20 characters")
    private String title;

    private UserSummaryDto user;

    private Set<TrackDto> tracks;

}
