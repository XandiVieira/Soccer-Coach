package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.User;
import com.faccaogames.soccercoach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User retrieveUserById(Long id) {
        return userRepository.getById(id);
    }

    public User retrieveUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    public void updateUser(User user, Long id){
        user.setId(id);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}