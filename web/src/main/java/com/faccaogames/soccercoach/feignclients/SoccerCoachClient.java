package com.faccaogames.soccercoach.feignclients;

import com.faccaogames.soccercoach.configuration.SoccerCoachClientConfig;
import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.model.Player;
import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "${soccerCoach.feign.name}", url = "${soccerCoach.feign.url}", configuration = SoccerCoachClientConfig.class)
public interface SoccerCoachClient {

    @GetMapping(value = "/league", produces = "application/json")
    List<League> getLeagues();

    @GetMapping(value = "/team", produces = "application/json")
    List<Team> getTeams();

    @GetMapping(value = "/player", produces = "application/json")
    List<Player> getPlayers();

    @GetMapping(value = "/user", produces = "application/json")
    List<User> getUsers();
}