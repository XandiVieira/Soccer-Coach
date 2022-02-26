package com.faccaogames.soccercoach.controller.thymeleaf;

import com.faccaogames.soccercoach.exception.ApiRequestException;
import com.faccaogames.soccercoach.model.User;
import com.faccaogames.soccercoach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping
public class MainController {

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

    @GetMapping("/registration")
    public ModelAndView showRegistrationPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("registration");
        mav.addObject(new User());
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("/index")
    public ModelAndView showHomePage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}