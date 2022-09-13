package com.atos.controller;

import com.atos.domain.exception.UserNotFoundException;
import com.atos.domain.request.UserRequest;
import com.atos.domain.response.UserResponse;
import com.atos.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("atos")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
    }

    @GetMapping("/retrieve-user")
    public UserResponse retrieveUserByUsername(@RequestParam String username) throws UserNotFoundException {
        return userService.retrieveUser(username);
    }
}
