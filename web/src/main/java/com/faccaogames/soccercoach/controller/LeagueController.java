package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping(path = "api/v1/league")
public class LeagueController extends BaseController {

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
}