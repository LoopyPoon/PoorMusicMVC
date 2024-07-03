package com.example.poormusic.dto.search_service_dto;

import com.example.poormusic.dto.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SearchAlbumDto extends BaseDto {
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private int year;
    @NonNull
    private List<SearchArtistDto> artists;
}
