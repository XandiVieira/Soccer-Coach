package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.User;
import com.faccaogames.soccercoach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public ModelAndView getAllUsers() {
        return createModelAndView("listUsers", userService.getAllUsers());
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") final Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@RequestParam final String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/{id}")
    public Long updateUser(@RequestBody User user, @PathVariable("id") final Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") final Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}/{teamId}")
    public String assumeTeam(@PathVariable("id") final Long id, @PathVariable("teamId") final Long teamId) {
        userService.assignNewCoach(id, teamId);
        return "";
    }

    private ModelAndView createModelAndView(String viewName, Object object) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addObject(object);
        return mav;
    }
}