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
    public void createPlayer(@RequestBody Player player) {
        playerService.createPlayer(player);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePlayer(@PathVariable("id") final Long id) {
        playerService.deletePlayer(id);
    }

    @PutMapping(value = "/{id}")
    public void editPlayer(@PathVariable("id") final Long id, @RequestBody Player player){
        playerService.updatePlayer(id, player);
    }
}