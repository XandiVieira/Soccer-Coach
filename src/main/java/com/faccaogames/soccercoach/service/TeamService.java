package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.exception.ApiRequestException;
import com.faccaogames.soccercoach.model.Player;
import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Long createTeam(Team team) {
        return teamRepository.save(team).getId();
    }

    public List<Team> retrieveAllTeams() {
        if (teamRepository.count() > 0) {
            return teamRepository.findAll();
        } else {
            throw new ApiRequestException("No teams were found.");
        }
    }

    public Team retrieveTeamById(Long id) {
        if (teamRepository.existsById(id)) {
            return teamRepository.getById(id);
        } else {
            throw new ApiRequestException("Team with id " + id + " not found.");
        }
    }

    public String deleteTeam(Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return "Team " + id + " was deleted.";
        } else {
            throw new ApiRequestException("Team with id " + id + " not found.");
        }
    }

    public Long updateTeam(Long id, Team team) {
        if (teamRepository.existsById(id)) {
            team.setId(id);
            return teamRepository.save(team).getId();
        } else {
            throw new ApiRequestException("Team with id " + id + " not found.");
        }
    }

    public void updateTeamPlayers(Team teamFrom, Team teamTo, Player player) {
        teamTo.getPlayers().add(player);
        if (teamFrom != null) {
            teamFrom.getPlayers().removeIf(player1 -> player1.getId().equals(player.getId()));
            updateTeam(teamFrom.getId(), teamFrom);
        }
        updateTeam(teamTo.getId(), teamTo);
    }
}