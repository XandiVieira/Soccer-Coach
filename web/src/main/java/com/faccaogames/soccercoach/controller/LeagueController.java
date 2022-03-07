package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/league")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping
    public ModelAndView getAllLeagues() {
        List<League> leagues = leagueService.getAllLeagues();
        return createModelAndView("chooseLeague", leagues);
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getLeagueById(@PathVariable("id") final Long id) {
        return createModelAndView("league", leagueService.getLeagueById(id));
    }

    @GetMapping(value = "/{continent}")
    public ModelAndView getLeagueByContinent(@PathVariable("continent") final String continent) {
        return createModelAndView("league", leagueService.getLeagueByContinent(continent));
    }

    private ModelAndView createModelAndView(String viewName, Object object) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addObject(object);
        return mav;
    }
}