package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.League;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueService {

    private final FeignService feignService;

    public LeagueService(FeignService feignService) {
        this.feignService = feignService;
    }

    public List<League> createLeagues(List<League> leagues) {
        return new ArrayList<>();
    }

    public List<League> getAllLeagues() {
        return feignService.getLeagues();
    }

    public League getLeagueById(Long id) {
        return new League();
    }

    public List<League> updateLeagues(List<League> leagues) {
        return new ArrayList<>();
    }

    public League updateLeague(Long id, League league) {
        return new League();
    }

    public String deleteLeague(Long id) {
        return new String();
    }
}