package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/league")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping(value = "/{id}")
    public League getLeague(@PathVariable("id") final Long id) {
        return leagueService.retrieveLeagueById(id);
    }

    @GetMapping
    public List<League> getAllLeagues() {
        return leagueService.retrieveAllLeagues();
    }

    @PostMapping
    public Long createLeague(@RequestBody League league) {
        return leagueService.createLeague(league);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteLeague(@PathVariable("id") final Long id) {
        return leagueService.deleteLeague(id);
    }

    @PutMapping(value = "/{id}")
    public Long editLeague(@PathVariable("id") final Long id, @RequestBody League league) {
        return leagueService.updateLeague(id, league);
    }
}