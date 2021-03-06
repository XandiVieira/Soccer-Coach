package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.exception.CustomAlreadyExistsException;
import com.faccaogames.soccercoach.exception.CustomNotFoundException;
import com.faccaogames.soccercoach.exception.CustomNotValidException;
import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.model.enums.Continent;
import com.faccaogames.soccercoach.model.enums.Country;
import com.faccaogames.soccercoach.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class LeagueService {

    private final LeagueRepository leagueRepository;

    @Autowired
    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public List<League> createLeagues(List<League> leagues) {
        validateLeagueCountry(leagues);
        validateLeagueContinent(leagues);
        validateLeagueAlreadyExistsByNameAndCountry(leagues);
        return leagueRepository.saveAll(leagues);
    }

    public void validateLeagueCountry(List<League> leagues) {
        for (League league : leagues) {
            if (!Arrays.stream(Country.values()).toList().stream().map(Enum::name).toList().contains(league.getCountry().toUpperCase(Locale.ROOT))) {
                throw new CustomNotValidException("Country " + league.getCountry() + " is invalid.");
            }
        }
    }

    public void validateLeagueContinent(List<League> leagues) {
        for (League league : leagues) {
            if (!Arrays.stream(Continent.values()).toList().stream().map(Enum::name).toList().contains(league.getContinent().toUpperCase(Locale.ROOT))) {
                throw new CustomNotValidException("Continent " + league.getContinent() + " is invalid.");
            }
        }
    }

    public void validateLeagueAlreadyExistsByNameAndCountry(List<League> leagues) {
        leagues.forEach(league -> {
            if (leagueRepository.existsByNameAndCountry(league.getName(), league.getCountry())) {
                throw new CustomAlreadyExistsException("League " + league.getName() + " already exists in " + league.getCountry());
            }
        });
    }

    public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    public League getLeagueById(Long id) {
        if (leagueRepository.existsById(id)) {
            return leagueRepository.getById(id);
        } else {
            throw new CustomNotFoundException("League with id " + id + " not found.");
        }
    }

    public List<League> getLeaguesByContinent(String continent) {
        if (leagueRepository.existsByContinent(continent)) {
            return leagueRepository.findByContinent(continent);
        } else {
            throw new CustomNotFoundException("League with continent " + continent + " not found.");
        }
    }

    public List<League> updateLeagues(List<League> leagues) {
        validateLeagueCountry(leagues);
        validateLeagueContinent(leagues);
        validateLeagueAlreadyExistsByNameAndCountry(leagues);
        validateLeagueExistsById(leagues);
        return leagueRepository.saveAll(leagues);
    }

    public void validateLeagueExistsById(List<League> leagues) {
        leagues.forEach(league -> {
            if (!leagueRepository.existsById(league.getId())) {
                throw new CustomNotFoundException("League with id " + league.getId() + " not found.");
            }
        });
    }

    public League updateLeague(Long id, League league) {
        validateLeagueCountry(Collections.singletonList(league));
        validateLeagueContinent(Collections.singletonList(league));
        validateLeagueAlreadyExistsByNameAndCountry(Collections.singletonList(league));
        validateLeagueExistsById(Collections.singletonList(league));
        league.setId(id);
        return leagueRepository.save(league);
    }

    public String deleteLeague(Long id) {
        if (leagueRepository.existsById(id)) {
            leagueRepository.deleteById(id);
            return "League " + id + " was deleted.";
        } else {
            throw new CustomNotFoundException("League with id " + id + " not found.");
        }
    }
}