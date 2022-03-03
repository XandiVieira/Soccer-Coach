package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "api/v1/player")
public class PlayerController {

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

    private ModelAndView createModelAndView(String viewName, Object object) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addObject(object);
        return mav;
    }
}