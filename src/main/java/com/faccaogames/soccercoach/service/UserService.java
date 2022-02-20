package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.exception.ApiRequestException;
import com.faccaogames.soccercoach.model.User;
import com.faccaogames.soccercoach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(User user) {
        return userRepository.save(user).getId();
    }

    public User retrieveUserById(Long id) {
        if (userRepository.existsById(id)) {
            return userRepository.getById(id);
        } else {
            throw new ApiRequestException("User with id " + id + " not found.");
        }
    }

    public User retrieveUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ApiRequestException("User with email " + email + " not found.");
        }
    }

    public Long updateUser(User user, Long id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user).getId();
        } else {
            throw new ApiRequestException("User with id " + id + " not found.");
        }
    }

    public String deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User " + id + " was deleted.";
        } else {
            throw new ApiRequestException("User with id " + id + " not found.");
        }
    }
}