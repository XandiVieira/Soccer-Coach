package com.faccaogames.soccercoach.controller.restapi;

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

    @PostMapping
    public List<Player> createPlayers(@RequestBody List<Player> players) {
        return playerService.createPlayers(players);
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping(value = "/{id}")
    public Player getPlayerById(@PathVariable("id") final Long id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping(value = "/{teamId}")
    public List<Player> getPlayersByTeamId(@PathVariable("teamId") final Long teamId) {
        return playerService.getPlayersByTeamId(teamId);
    }

    @PutMapping
    public List<Player> updatePlayers(@RequestBody List<Player> players) {
        return playerService.updatePlayers(players);
    }

    @PutMapping(value = "/{id}")
    public Player updatePlayer(@PathVariable("id") final Long id, @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }

    @DeleteMapping(value = "/{id}")
    public String deletePlayer(@PathVariable("id") final Long id) {
        return playerService.deletePlayer(id);
    }

    @PutMapping(value = "/{id}/{teamId}")
    public void transferPlayer(@PathVariable("id") final Long id, @PathVariable("teamId") final long teamId) {
        playerService.transferPlayer(id, teamId);
    }
}