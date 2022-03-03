package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "api/v1/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ModelAndView getAllTeams() {
        return createModelAndView("chooseTeam", teamService.getAllTeams());
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getTeamById(@PathVariable("id") final Long id) {
        return createModelAndView("team", teamService.getTeamById(id));
    }

    @PutMapping(value = "/{id}")
    public ModelAndView updateTeam(@PathVariable("id") final Long id, @RequestBody Team team) {
        return createModelAndView("team", teamService.updateTeam(id, team));
    }

    private ModelAndView createModelAndView(String viewName, Object object) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addObject(object);
        return mav;
    }
}