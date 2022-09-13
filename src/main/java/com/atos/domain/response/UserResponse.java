package com.atos.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String username;
    private String country;
    private LocalDate birthDate;
    private String phone;
    private String gender;
}
