package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.League;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueService {

    private final FeignService feignService;

    public LeagueService(FeignService feignService) {
        this.feignService = feignService;
    }

    public List<League> getAllLeagues() {
        return feignService.getLeagues();
    }

    public League getLeagueById(Long id) {
        return feignService.getLeagueById(id);
    }

    public List<League> getLeagueByContinent(String continent) {
        return feignService.getLeagueByContinent(continent);
    }
}