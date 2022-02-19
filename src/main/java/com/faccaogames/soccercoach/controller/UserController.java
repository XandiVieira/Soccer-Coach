package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.User;
import com.faccaogames.soccercoach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.retrieveUserById(id);
    }

    @GetMapping("/{email}")
    public User getUserById(@PathVariable("email") String email) {
        return userService.retrieveUserByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public void editUser(@RequestBody User user, @PathVariable("id") Long id) {
        userService.updateUser(user, id);
    }
}