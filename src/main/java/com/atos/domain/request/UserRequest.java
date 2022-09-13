package com.atos.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String username;
    private String country;
    private String birthDate;
    private String phone;
    private String gender;
}
