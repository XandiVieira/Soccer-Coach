package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/continent")
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    @GetMapping
    public ModelAndView getAllContinents() {
        List<String> leagues = continentService.getAllContinents();
        return createModelAndView("chooseLeague", leagues);
    }

    private ModelAndView createModelAndView(String viewName, Object object) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addObject(object);
        return mav;
    }
}