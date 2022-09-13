package com.atos.service;

import com.atos.domain.exception.UserNotFoundException;
import com.atos.domain.request.UserRequest;
import com.atos.domain.response.UserResponse;
import com.atos.mapper.UserMapper;
import com.atos.model.entity.User;
import com.atos.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest userRequest) {
        LOGGER.info(String.format("Saving user [%s]", userRequest.getUsername()));
        var user = UserMapper.toEntity(userRequest);
        return userRepository.save(user);
    }

    public UserResponse retrieveUser(String username) throws UserNotFoundException {
        LOGGER.info(String.format("Retrieving user [%s]", username));
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("Cannot find a user with username [%s]", username)));
        return UserMapper.toDTO(user);
    }
}
