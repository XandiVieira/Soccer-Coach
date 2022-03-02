package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.exception.CustomAlreadyExistsException;
import com.faccaogames.soccercoach.feignclients.SoccerCoachClient;
import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.model.Player;
import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.model.User;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeignService {

    private final SoccerCoachClient soccerCoachClient;

    public FeignService(SoccerCoachClient soccerCoachClient) {
        this.soccerCoachClient = soccerCoachClient;
    }

    public List<League> getLeagues() {
        try {
            return soccerCoachClient.getLeagues();
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }

    public List<Player> getPlayers() {
        try {
            return soccerCoachClient.getPlayers();
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }

    public List<Team> getTeams() {
        try {
            return soccerCoachClient.getTeams();
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }

    public List<User> getUsers() {
        try {
            return soccerCoachClient.getUsers();
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }
}
