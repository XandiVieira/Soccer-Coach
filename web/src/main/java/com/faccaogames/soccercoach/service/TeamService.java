package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.Team;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TeamService extends BaseService {

    @Autowired
    public TeamService(FeignService feignService) {
        super(feignService);
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
}