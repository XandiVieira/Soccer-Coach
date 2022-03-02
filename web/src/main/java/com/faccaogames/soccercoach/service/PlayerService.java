package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final FeignService feignService;

    public PlayerService(FeignService feignService) {
        this.feignService = feignService;
    }

    public List<Player> createPlayers(List<Player> players) {
        return new ArrayList<>();
    }

    public List<Player> getAllPlayers() {
        return feignService.getPlayers();
    }

    public Player getPlayerById(Long id) {
        return new Player();
    }

    public List<Player> getPlayersByTeamId(Long teamId) {
        return new ArrayList<>();
    }

    public List<Player> updatePlayers(List<Player> players) {
        return new ArrayList<>();
    }

    public Player updatePlayer(Long id, Player player) {
        return new Player();
    }

    public String deletePlayer(Long id) {
        return new String();
    }

    public void transferPlayer(Long id, long teamId) {
    }
}