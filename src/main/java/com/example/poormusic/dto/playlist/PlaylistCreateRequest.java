package com.example.poormusic.dto.playlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistCreateRequest {
    @NotBlank(message = "Playlist name cannot be empty")
    @Size(min = 1, max = 20, message = "Playlist name must be between 2 and 20 character")
    private String title;

    @Pattern(regexp = "public|private", message = "Kind must be 'public' or 'private'")
    private String kind;
}
