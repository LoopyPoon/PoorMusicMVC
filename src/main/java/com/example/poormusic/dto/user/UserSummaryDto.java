package com.example.poormusic.dto.user;


import com.example.poormusic.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserSummaryDto extends BaseDto {

    private Long id;

    @NotEmpty(message = "Email should be not empty")
    @Email
    private String email;

    @NotEmpty(message = "Username should be not empty")
    private String username;

}
