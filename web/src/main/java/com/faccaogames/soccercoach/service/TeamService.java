package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.Player;
import com.faccaogames.soccercoach.model.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private final FeignService feignService;

    public TeamService(FeignService feignService) {
        this.feignService = feignService;
    }

    public List<Team> createTeams(List<Team> teams) {
        return new ArrayList<>();
    }

    public List<Team> getAllTeams() {
        return feignService.getTeams();
    }

    public Team getTeamById(Long id) {
        return feignService.getTeamById(id);
    }

    public List<Team> updateTeams(List<Team> teams) {
        return new ArrayList<>();
    }

    public Team updateTeam(Long id, Team team) {
        return new Team();
    }

    public String deleteTeam(Long id) {
        return new String();
    }

    public List<Player> getTeamPlayers(Long id) {
        return feignService.getPlayersByTeamId(id);
    }
}