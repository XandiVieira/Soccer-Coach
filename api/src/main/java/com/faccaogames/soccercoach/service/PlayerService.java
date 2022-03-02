package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.exception.CustomNotFoundException;
import com.faccaogames.soccercoach.model.Player;
import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
    }

    public List<Player> createPlayers(List<Player> players) {
        validatePlayerName(players);
        List<Player> savedPlayers = playerRepository.saveAll(players);
        savedPlayers.forEach(player -> transferPlayer(player.getId(), player.getTeamId()));
        return savedPlayers;
    }

    private void validatePlayerName(List<Player> players) {
        players.forEach(player -> {
            if (player.getFirstName().isBlank() && player.getLastName().isBlank()) {
                throw new CustomNotFoundException("Player firstname or lastname must be informed.");
            }
        });
    }

    public List<Player> getAllPlayers() {
        if (playerRepository.count() > 0) {
            return playerRepository.findAll();
        } else {
            throw new CustomNotFoundException("No players were found.");
        }
    }

    public Player getPlayerById(Long id) {
        if (playerRepository.existsById(id)) {
            return playerRepository.getById(id);
        } else {
            throw new CustomNotFoundException("Player with id " + id + " not found.");
        }
    }

    public List<Player> getPlayersByTeamId(Long teamId) {
        if (playerRepository.existsByTeamId(teamId)) {
            return playerRepository.getByTeamId(teamId);
        } else {
            throw new CustomNotFoundException("Player does not belong to this team.");
        }
    }

    public List<Player> updatePlayers(List<Player> players) {
        validatePlayerName(players);
        validatePlayerExistsById(players);
        return playerRepository.saveAll(players);
    }

    private void validatePlayerExistsById(List<Player> players) {
        players.forEach(player -> {
            if (!playerRepository.existsById(player.getId())) {
                throw new CustomNotFoundException("Player with id " + player.getId() + " not found.");
            }
        });
    }

    public Player updatePlayer(Long id, Player player) {
        validatePlayerName(Collections.singletonList(player));
        validatePlayerExistsById(Collections.singletonList(player));
        player.setId(id);
        return playerRepository.save(player);
    }

    public String deletePlayer(Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return "Player " + id + " was deleted.";
        } else {
            throw new CustomNotFoundException("Player with id " + id + " not found.");
        }
    }

    public void transferPlayer(Long playerId, Long teamId) {
        Player player = getPlayerById(playerId);
        Team currentTeam = null;
        if (!player.getTeamId().equals(teamId)) {
            currentTeam = teamService.getTeamById(player.getTeamId());
        }
        Team team = teamService.getTeamById(teamId);

        completeTransfer(player, teamId);
        updateTeamsTransfer(currentTeam, team, player);
    }

    private void completeTransfer(Player player, Long teamId) {
        player.setTeamId(teamId);
        playerRepository.save(player);
    }

    private void updateTeamsTransfer(Team currentTeam, Team team, Player player) {
        teamService.updateTeamPlayers(currentTeam, team, player);
    }
}