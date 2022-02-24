package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.exception.ApiRequestException;
import com.faccaogames.soccercoach.model.User;
import com.faccaogames.soccercoach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ModelAndView createUser(@ModelAttribute @Valid User user) {
        try {
            userService.createUser(user);
        } catch (ApiRequestException are) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }
        return new ModelAndView("login");
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

    @PutMapping("/{id}/{teamId}")
    public String assumeTeam(@PathVariable("id") final Long id, @PathVariable("teamId") final Long teamId) {
        userService.assignNewCoach(id, teamId);
        return "";
    }
}