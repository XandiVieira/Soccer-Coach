package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/{id}")
    public Team getTeam(@PathVariable("id") final Long id) {
        return teamService.retrieveTeamById(id);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.retrieveAllTeams();
    }

    @PostMapping
    public Long createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteTeam(@PathVariable("id") final Long id) {
        return teamService.deleteTeam(id);
    }

    @PutMapping(value = "/{id}")
    public Long editTeam(@PathVariable("id") final Long id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }
}