package com.atos.service;

import com.atos.AtosApplicationTests;
import com.atos.domain.exception.BadRequestException;
import com.atos.domain.exception.UserNotFoundException;
import com.atos.domain.request.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = AtosApplicationTests.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Sql(scripts = {"classpath:sql/user.sql"})
    void retrieveUser_userDoesNotExist_ExceptionIsThrow() {
        // GIVEN
        String username = "New User";

        // WHEN & THEN
        assertThrows(UserNotFoundException.class,() -> userService.retrieveUser(username));
    }

    @Test
    @Sql(scripts = {"classpath:sql/user.sql"})
    void retrieveUser_userExist_userReturned() throws UserNotFoundException {
        // GIVEN
        String username = "Riadh";

        // WHEN
        var user = userService.retrieveUser(username);

        // THEN
        assertEquals("Riadh", user.getUsername());
        assertEquals("MALE", user.getGender());
        assertNull(user.getPhone());
        assertEquals("France", user.getCountry());
    }

    @Test
    @Sql(scripts = {"classpath:sql/user.sql"})
    void createUser_dataIsGood_userCreated() {
        // GIVEN
        var user = new UserRequest(
                "Atos",
                "France",
                "1995-06-17",
                null,
                null
        );

        // WHEN
        var result = userService.createUser(user);

        // THEN
        assertEquals("Atos", result.getUsername());
        assertEquals("France", result.getCountry());
    }

    @Test
    @Sql(scripts = {"classpath:sql/user.sql"})
    void createUser_countryIsNotFrance_exceptionThrown() {
        // GIVEN
        var user = new UserRequest(
                "Atos",
                "Tunisia",
                "1995-06-17",
                null,
                null
        );

        // WHEN & THEN
        assertThrows(BadRequestException.class,() -> userService.createUser(user));
    }

    @Test
    @Sql(scripts = {"classpath:sql/user.sql"})
    void createUser_minorUser_exceptionThrown() {
        // GIVEN
        var user = new UserRequest(
                "Atos",
                "France",
                "2012-06-17",
                null,
                null
        );

        // WHEN & THEN
        assertThrows(BadRequestException.class,() -> userService.createUser(user));
    }

}
