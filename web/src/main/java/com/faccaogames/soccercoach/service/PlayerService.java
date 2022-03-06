package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PlayerService extends BaseService {

    @Autowired
    public PlayerService(FeignService feignService) {
        super(feignService);
    }

    public List<Player> createPlayers(List<Player> players) {
        return new ArrayList<>();
    }

    public List<Player> getAllPlayers() {
        return feignService.getPlayers();
    }

    public Player getPlayerById(Long id) {
        return feignService.getPlayerById(id);
    }

    public List<Player> getPlayersByTeamId(Long teamId) {
        return feignService.getPlayersByTeamId(teamId);
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

    public void transferPlayer(Long playerId, long teamId) {
        feignService.transferPlayer(playerId, teamId);
    }
}