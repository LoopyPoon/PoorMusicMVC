package com.example.poormusic.dto.search_service_dto;

import com.example.poormusic.dto.AlbumDto;
import com.example.poormusic.dto.BaseDto;
import com.example.poormusic.dto.GenreDto;
import com.example.poormusic.dto.TrackDto;
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
public class SearchArtistDto extends BaseDto {

    private Long id;

    @NotBlank
    private String title;

}