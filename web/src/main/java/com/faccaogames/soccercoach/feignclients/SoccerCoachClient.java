package com.faccaogames.soccercoach.feignclients;

import com.faccaogames.soccercoach.configuration.SoccerCoachClientConfig;
import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.model.Player;
import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${soccerCoach.feign.name}", url = "${soccerCoach.feign.url}", configuration = SoccerCoachClientConfig.class)
public interface SoccerCoachClient {

    @GetMapping(value = "/league", produces = "application/json")
    List<League> getLeagues();

    @GetMapping(value = "/league/{id}", produces = "application/json")
    League getLeagueById(@PathVariable("id") Long id);

    @GetMapping(value = "/league/{continent}", produces = "application/json")
    List<League> getLeagueByContinent(String continent);

    @GetMapping(value = "/team", produces = "application/json")
    List<Team> getTeams();

    @GetMapping(value = "/team/{id}", produces = "application/json")
    Team getTeamById(@PathVariable("id") Long id);

    @GetMapping(value = "/player", produces = "application/json")
    List<Player> getPlayers();

    @GetMapping(value = "/player/{teamId}", produces = "application/json")
    List<Player> getPlayersByTeamId(@PathVariable("id") Long id);

    @GetMapping(value = "/player/{id}", produces = "application/json")
    Player getPlayerById(@PathVariable("id") Long id);

    @PutMapping(value = "/player/{playerId}/{teamId}", produces = "application/json")
    void transferPlayer(@PathVariable("playerId") Long playerId, @PathVariable("teamId") Long teamId);

    @GetMapping(value = "/user", produces = "application/json")
    List<User> getUsers();

    @PostMapping(value = "/user", produces = "application/json")
    User createUser(@RequestBody User user);

    @GetMapping(value = "/user/{id}", produces = "application/json")
    User getUserById(@PathVariable("id") Long id);

    @GetMapping(value = "/user/{email}", produces = "application/json")
    User getUserByEmail(@PathVariable("email") String email);

    @PutMapping(value = "/user/{id}", produces = "application/json")
    Long updateUser(@RequestBody User user, @PathVariable("id") Long id);

    @DeleteMapping(value = "/user/{id}", produces = "application/json")
    String deleteUser(@PathVariable("id") Long id);

    @PutMapping(value = "/user/{id}/{teamId}", produces = "application/json")
    void assignNewCoach(@PathVariable("id") Long userId, @PathVariable("teamId") Long teamId);

    @GetMapping(value = "/continent", produces = "application/json")
    List<String> getContinents();
}