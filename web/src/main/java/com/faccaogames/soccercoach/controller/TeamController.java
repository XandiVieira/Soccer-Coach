package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public List<Team> createTeams(@RequestBody List<Team> teams) {
        return teamService.createTeams(teams);
    }

    @GetMapping
    public ModelAndView getAllTeams() {
        return createModelAndView("chooseTeam", teamService.getAllTeams());
    }

    private ModelAndView createModelAndView(String viewName, Object object) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addObject(object);
        return mav;
    }

    @GetMapping(value = "/{id}")
    public Team getTeamById(@PathVariable("id") final Long id) {
        return teamService.getTeamById(id);
    }

    @PutMapping
    public List<Team> updateTeams(@RequestBody List<Team> teams) {
        return teamService.updateTeams(teams);
    }

    @PutMapping(value = "/{id}")
    public Team updateTeam(@PathVariable("id") final Long id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteTeam(@PathVariable("id") final Long id) {
        return teamService.deleteTeam(id);
    }
}