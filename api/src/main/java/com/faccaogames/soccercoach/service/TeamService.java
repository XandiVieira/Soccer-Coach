package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.Player;
import com.faccaogames.soccercoach.model.enums.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.faccaogames.soccercoach.exception.CustomAlreadyExistsException;
import com.faccaogames.soccercoach.exception.CustomNotFoundException;
import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.repository.TeamRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> createTeams(List<Team> teams) {
        validateTeamCountry(teams);
        validateTeamAlreadyExistsByNameAndCountry(teams);
        return teamRepository.saveAll(teams);
    }

    private void validateTeamAlreadyExistsByNameAndCountry(List<Team> teams) {
        teams.forEach(team -> {
            if (teamRepository.existsByNameAndCountry(team.getName(), team.getCountry())) {
                throw new CustomAlreadyExistsException("Team " + team.getName() + " already exists in " + team.getCountry());
            }
        });
    }

    private void validateTeamCountry(List<Team> teams) {
        for (Team team : teams) {
            if (!Arrays.stream(Country.values()).toList().contains(team.getCountry().toUpperCase(Locale.ROOT))) {
                throw new CustomNotFoundException("Country " + team.getCountry() + " is invalid.");
            }
        }
    }

    public List<Team> getAllTeams() {
        if (teamRepository.count() > 0) {
            return teamRepository.findAll();
        } else {
            throw new CustomNotFoundException("No teams were found.");
        }
    }

    public Team getTeamById(Long id) {
        if (teamRepository.existsById(id)) {
            return teamRepository.getById(id);
        } else {
            throw new CustomNotFoundException("Team with id " + id + " not found.");
        }
    }

    public List<Team> updateTeams(List<Team> teams) {
        validateTeamCountry(teams);
        validateTeamAlreadyExistsByNameAndCountry(teams);
        validateTeamAlreadyExistsById(teams);
        return teamRepository.saveAll(teams);
    }

    private void validateTeamAlreadyExistsById(List<Team> teams) {
        teams.forEach(team -> {
            if (!teamRepository.existsById(team.getId())) {
                throw new CustomNotFoundException("Team with id " + team.getId() + " not found.");
            }
        });
    }

    public Team updateTeam(Long id, Team team) {
        validateTeamCountry(Collections.singletonList(team));
        validateTeamAlreadyExistsByNameAndCountry(Collections.singletonList(team));
        validateTeamAlreadyExistsById(Collections.singletonList(team));
        team.setId(id);
        return teamRepository.save(team);
    }

    public String deleteTeam(Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return "Team " + id + " was deleted.";
        } else {
            throw new CustomNotFoundException("Team with id " + id + " not found.");
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

    public List<Team> getTeamByCountry(String country) {
        return teamRepository.getByCountry(country);
    }
}