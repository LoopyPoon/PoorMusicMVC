package com.example.poormusic.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDto {
    LocalDateTime created;
    LocalDateTime updated;
}
