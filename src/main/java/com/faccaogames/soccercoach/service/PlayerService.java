package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.Player;
import com.faccaogames.soccercoach.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void createPlayer(Player player) {
        playerRepository.save(player);
    }

    public List<Player> retrieveAllPlayers() {
        return playerRepository.findAll();
    }

    public Player retrievePlayerById(Long id) {
        return playerRepository.getById(id);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    public void updatePlayer(Long id, Player player) {
        player.setId(id);
        playerRepository.save(player);
    }
}