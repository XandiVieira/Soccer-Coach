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
    public Long createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") final Long id) {
        return userService.retrieveUserById(id);
    }

    @GetMapping
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
}