package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "api/v1/player")
public class PlayerController extends BaseController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ModelAndView getAllPlayers() {
        return createModelAndView("listPlayers", playerService.getAllPlayers());
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getPlayerById(@PathVariable("id") final Long id) {
        return createModelAndView("player", playerService.getPlayerById(id));
    }

    @GetMapping(value = "/{teamId}")
    public ModelAndView getPlayersByTeamId(@PathVariable("teamId") final Long teamId) {
        return createModelAndView("teamPlayers", playerService.getPlayersByTeamId(teamId));
    }

    @PutMapping(value = "/{id}/{teamId}")
    public void transferPlayer(@PathVariable("id") final Long id, @PathVariable("teamId") final long teamId) {
        playerService.transferPlayer(id, teamId);
    }
}