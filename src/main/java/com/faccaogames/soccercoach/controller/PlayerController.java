package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.Player;
import com.faccaogames.soccercoach.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/player")
public class PlayerController {

    private PlayerService playerService;

    @GetMapping(path = "{playerId}")
    public Player getPlayer(Long id) {
        return playerService.retrievePlayerById(id);
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.retrieveAllPlayers();
    }

    @PostMapping
    public void createPlayer(Player player) {
        playerService.createPlayer(player);
    }

}
