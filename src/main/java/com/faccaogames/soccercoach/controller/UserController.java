package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.exception.ApiRequestException;
import com.faccaogames.soccercoach.model.User;
import com.faccaogames.soccercoach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/createuser")
    public ModelAndView createUser(@ModelAttribute @Valid User user, HttpServletRequest httpsr, Errors errors) {
        User userCreated;
        try {
            userCreated = userService.createUser(user);
        } catch (ApiRequestException are) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }
        return new ModelAndView("successRegister", "user", userCreated);
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