package com.faccaogames.soccercoach.controller.web;

import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping("/chooseLeague")
    private ModelAndView getLeagues() {
        List<League> leagues = leagueService.getAllLeagues();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("chooseLeague");
        mav.addObject(leagues);
        return mav;
    }
}