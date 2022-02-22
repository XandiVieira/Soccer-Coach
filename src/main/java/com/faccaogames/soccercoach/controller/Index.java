package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.User;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {

    @GetMapping("api/v1/index")
    public String index(Model model){

        model.addAttribute("user", new User() );


        return "index";

    }
}
