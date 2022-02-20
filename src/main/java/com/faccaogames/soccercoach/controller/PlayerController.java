package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.Player;
import com.faccaogames.soccercoach.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/{id}")
    public Player getPlayer(@PathVariable("id") final Long id) {
        return playerService.retrievePlayerById(id);
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.retrieveAllPlayers();
    }

    @PostMapping
    public Long createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @DeleteMapping(value = "/{id}")
    public String deletePlayer(@PathVariable("id") final Long id) {
        return playerService.deletePlayer(id);
    }

    @PutMapping(value = "/{id}")
    public Long editPlayer(@PathVariable("id") final Long id, @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }

    @PutMapping(value = "/{id}/{teamId}")
    public void transferPlayer(@PathVariable("id") final Long id, @PathVariable("teamId") final long teamId) {
        playerService.transferPlayer(id, teamId);
    }
}