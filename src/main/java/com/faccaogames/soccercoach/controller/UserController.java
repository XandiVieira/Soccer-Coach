package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.User;
import com.faccaogames.soccercoach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") final Long id) {
        return userService.retrieveUserById(id);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.retrieveUsers();
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@RequestParam final String email) {
        return userService.retrieveUserByEmail(email);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") final Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public Long editUser(@RequestBody User user, @PathVariable("id") final Long id) {
        return userService.updateUser(user, id);
    }

    @PutMapping("/{id}/{teamId}")
    public String assumeTeam(@PathVariable("id") final Long id, @PathVariable("teamId") final Long teamId) {
        userService.assignNewCoach(id, teamId);
        return "";
    }
}