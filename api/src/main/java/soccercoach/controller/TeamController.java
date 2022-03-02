package soccercoach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soccercoach.model.Team;
import soccercoach.service.TeamService;

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
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
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