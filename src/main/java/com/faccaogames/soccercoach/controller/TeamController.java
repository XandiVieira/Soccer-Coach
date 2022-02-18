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
    public void createTeam(@RequestBody Team team) {
        teamService.createTeam(team);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTeam(@PathVariable("id") final Long id) {
        teamService.deleteTeam(id);
    }

    @PutMapping(value = "/{id}")
    public void editTeam(@PathVariable("id") final Long id, @RequestBody Team team) {
        teamService.updateTeam(id, team);
    }
}