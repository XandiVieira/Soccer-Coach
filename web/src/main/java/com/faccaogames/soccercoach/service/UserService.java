package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

private final FeignService feignService;

    public UserService(FeignService feignService) {
        this.feignService = feignService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public User createUser(User user) {
        return new User();
    }

    public List<User> getAllUsers() {
        return feignService.getUsers();
    }

    public User getUserById(Long id) {
        return new User();
    }

    public User getUserByEmail(String email) {
        return new User();
    }

    public Long updateUser(User user, Long id) {
        return 0L;
    }

    public String deleteUser(Long id) {
        return new String();
    }

    public void assignNewCoach(Long id, Long teamId) {
    }
}