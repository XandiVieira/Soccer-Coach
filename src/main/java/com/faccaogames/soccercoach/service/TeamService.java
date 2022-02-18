package com.faccaogames.soccercoach.service;

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

    public void createTeam(Team team) {
        teamRepository.save(team);
    }

    public List<Team> retrieveAllTeams() {
        return teamRepository.findAll();
    }

    public Team retrieveTeamById(Long id) {
        return teamRepository.getById(id);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public void updateTeam(Long id, Team team) {
        team.setId(id);
        teamRepository.save(team);
    }
}