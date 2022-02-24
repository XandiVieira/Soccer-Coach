package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class MainController {

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

    @GetMapping("/")
    public ModelAndView showHomePage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}