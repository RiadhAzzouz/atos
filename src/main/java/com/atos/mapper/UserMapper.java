package com.atos.mapper;

import com.atos.domain.exception.BadRequestException;
import com.atos.domain.request.UserRequest;
import com.atos.domain.response.UserResponse;
import com.atos.model.entity.User;

import java.time.LocalDate;
import java.time.Period;

import static org.apache.logging.log4j.util.Strings.isBlank;

public final class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toDTO(User user) {
        return new UserResponse(
               user.getUsername(),
                user.getCountry(),
               user.getBirthDate(),
               user.getPhone(),
               user.getGender()
        );
    }

    public static User toEntity(UserRequest userRequest) {
        validateBirthDate(userRequest);
        validateUsername(userRequest);
        validateCountry(userRequest);
        return new User(
                userRequest.getUsername(),
                userRequest.getCountry(),
                LocalDate.parse(userRequest.getBirthDate()),
                userRequest.getPhone(),
                userRequest.getGender()
        );
    }

    private static void validateCountry(UserRequest userRequest) {
        if (isBlank(userRequest.getCountry()) || !userRequest.getCountry().equalsIgnoreCase("FRANCE")) {
            throw new BadRequestException("Wrong country input");
        }
    }

    private static void validateUsername(UserRequest userRequest) {
        if (isBlank(userRequest.getUsername())) {
            throw new BadRequestException("Username should not be null");
        }
    }

    private static void validateBirthDate(UserRequest userRequest) {
        if (userRequest.getBirthDate() != null) {
            int age = Period.between(LocalDate.parse(userRequest.getBirthDate()), LocalDate.now()).getYears();
            if (age < 18) {
                throw new BadRequestException(String.format("Your age [%d], you are still a minor", age));
            }
        } else {
            throw new BadRequestException("Birthdate should not be null");
        }
    }
}
