package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.League;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LeagueService extends BaseService {

    @Autowired
    public LeagueService(FeignService feignService) {
        super(feignService);
    }

    public List<League> getAllLeagues() {
        return feignService.getLeagues();
    }

    public League getLeagueById(Long id) {
        return feignService.getLeagueById(id);
    }
}