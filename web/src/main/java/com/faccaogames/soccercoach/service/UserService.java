package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserService extends BaseService implements UserDetailsService {

    @Autowired
    public UserService(FeignService feignService) {
        super(feignService);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public User createUser(User user) {
        return feignService.createUser(user);
    }

    public List<User> getAllUsers() {
        return feignService.getUsers();
    }

    public User getUserById(Long id) {
        return feignService.getUserById(id);
    }

    public User getUserByEmail(String email) {
        return feignService.getUserByEmail(email);
    }

    public Long updateUser(User user, Long id) {
        return feignService.updateUser(user, id);
    }

    public String deleteUser(Long id) {
        return feignService.deleteUser(id);
    }

    public void assignNewCoach(Long userId, Long teamId) {
        feignService.assignNewCoach(userId, teamId);
    }
}