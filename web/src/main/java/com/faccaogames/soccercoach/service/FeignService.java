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

    public League getLeagueById(Long id) {
        try {
            return soccerCoachClient.getLeagueById(id);
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

    public Player getPlayerById(Long id) {
        try {
            return soccerCoachClient.getPlayerById(id);
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }

    public List<Player> getPlayersByTeamId(Long teamId) {
        try {
            return soccerCoachClient.getPlayersByTeamId(teamId);
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

    public Team getTeamById(Long id) {
        try {
            return soccerCoachClient.getTeamById(id);
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }

    public void transferPlayer(Long playerId, long teamId) {
        try {
            soccerCoachClient.transferPlayer(playerId, teamId);
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

    public User createUser(User user) {
        try {
            return soccerCoachClient.createUser(user);
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }

    public User getUserById(Long id) {
        try {
            return soccerCoachClient.getUserById(id);
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }

    public User getUserByEmail(String email) {
        try {
            return soccerCoachClient.getUserByEmail(email);
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }

    public Long updateUser(User user, Long id) {
        try {
            return soccerCoachClient.updateUser(user, id);
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }

    public String deleteUser(Long id) {
        try {
            return soccerCoachClient.deleteUser(id);
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }

    public void assignNewCoach(Long userId, Long teamId) {
        try {
            soccerCoachClient.assignNewCoach(userId, teamId);
        } catch (FeignException fe) {
            throw new CustomAlreadyExistsException(fe.getMessage());
        }
    }
}
