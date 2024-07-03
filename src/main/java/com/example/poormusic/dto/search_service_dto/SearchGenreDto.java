package com.example.poormusic.dto.search_service_dto;

import com.example.poormusic.dto.AlbumDto;
import com.example.poormusic.dto.ArtistDto;
import com.example.poormusic.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SearchGenreDto extends BaseDto {

    private Long id;

    @NonNull
    private String title;

}
