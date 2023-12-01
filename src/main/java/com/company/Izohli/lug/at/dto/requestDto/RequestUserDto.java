package com.company.Izohli.lug.at.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RequestUserDto {

    private String firstname;
    private String lastname;
    @NotBlank(message = "Username cannot be null or empty!")
    private String username;
    @NotBlank(message = "Username cannot be null or empty!")
    private String password;

}
