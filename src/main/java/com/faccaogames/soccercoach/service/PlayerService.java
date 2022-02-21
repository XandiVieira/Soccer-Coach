package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.exception.ApiRequestException;
import com.faccaogames.soccercoach.model.Player;
import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Long createPlayer(Player player) {
        Long id = playerRepository.save(player).getId();
        transferPlayer(player.getId(), player.getTeamId());
        return id;
    }

    public List<Player> retrieveAllPlayers() {
        if (playerRepository.count() > 0) {
            return playerRepository.findAll();
        } else {
            throw new ApiRequestException("No players were found.");
        }
    }

    public Player retrievePlayerById(Long id) {
        if (playerRepository.existsById(id)) {
            return playerRepository.getById(id);
        } else {
            throw new ApiRequestException("Player with id " + id + " not found.");
        }
    }

    public String deletePlayer(Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return "Player " + id + " was deleted.";
        } else {
            throw new ApiRequestException("Player with id " + id + " not found.");
        }
    }

    public Long updatePlayer(Long id, Player player) {
        if (playerRepository.existsById(id)) {
            player.setId(id);
            return playerRepository.save(player).getId();
        } else {
            throw new ApiRequestException("Player with id " + id + " not found.");
        }
    }

    public void transferPlayer(Long id, long teamId) {
        Player player = playerRepository.getById(id);
        Team currentTeam = teamService.retrieveTeamById(player.getTeamId());
        Team team = teamService.retrieveTeamById(teamId);

        doTransfer(player, teamId);
        updateTeams(currentTeam, team, player);
    }

    private void updateTeams(Team currentTeam, Team team, Player player) {
        team.getPlayers().add(player);
        currentTeam.getPlayers().removeIf(player1 -> player1.getId().equals(player.getId()));
        teamService.updateTeam(currentTeam.getId(), currentTeam);
        teamService.updateTeam(team.getId(), team);
    }

    private void doTransfer(Player player, long teamId) {
        player.setTeamId(teamId);
        playerRepository.save(player);
    }
}