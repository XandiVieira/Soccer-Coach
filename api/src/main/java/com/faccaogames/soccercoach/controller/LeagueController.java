package com.faccaogames.soccercoach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.service.LeagueService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/league")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @PostMapping
    public List<League> createLeagues(@RequestBody List<League> leagues) {
        return leagueService.createLeagues(leagues);
    }

    @GetMapping
    public List<League> getAllLeagues()  {
        return leagueService.getAllLeagues();
    }

    @GetMapping(value = "/{id}")
    public League getLeagueById(@PathVariable("id") final Long id) {
        return leagueService.getLeagueById(id);
    }

    @PutMapping
    public List<League> updateLeagues(@RequestBody List<League> leagues) {
        return leagueService.updateLeagues(leagues);
    }

    @PutMapping(value = "/{id}")
    public League updateLeague(@PathVariable("id") final Long id, @RequestBody League league) {
        return leagueService.updateLeague(id, league);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteLeague(@PathVariable("id") final Long id) {
        return leagueService.deleteLeague(id);
    }
}