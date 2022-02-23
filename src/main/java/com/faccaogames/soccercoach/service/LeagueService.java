package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.exception.ApiRequestException;
import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueService {

    private final LeagueRepository leagueRepository;

    @Autowired
    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public Long createLeague(League league) {
        league.setId(leagueRepository.save(league).getId());
        return league.getId();
    }

    public League retrieveLeagueById(Long id) {
        if (leagueRepository.existsById(id)) {
            return leagueRepository.getById(id);
        } else {
            throw new ApiRequestException("League with id " + id + " not found.");
        }
    }

    public Long updateLeague(Long id, League league) {
        if (leagueRepository.existsById(id)) {
            league.setId(id);
            return leagueRepository.save(league).getId();
        } else {
            throw new ApiRequestException("League with id " + id + " not found.");
        }
    }

    public String deleteLeague(Long id) {
        if (leagueRepository.existsById(id)) {
            leagueRepository.deleteById(id);
            return "League " + id + " was deleted.";
        } else {
            throw new ApiRequestException("League with id " + id + " not found.");
        }
    }

    public List<League> retrieveAllLeagues() {
        return leagueRepository.findAll();
    }
}