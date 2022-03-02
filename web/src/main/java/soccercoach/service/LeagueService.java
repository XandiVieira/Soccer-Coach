package soccercoach.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soccercoach.model.League;

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
        validateLeagueAlreadyExistsByNameAndCountry(leagues);
        return leagueRepository.saveAll(leagues);
    }

    private void validateLeagueCountry(List<League> leagues) {
        for (League league : leagues) {
            if (!Arrays.stream(Country.values()).toList().contains(league.getCountry().toUpperCase(Locale.ROOT))) {
                throw new CustomNotFoundException("Country " + league.getCountry() + " is invalid.");
            }
        }
    }

    private void validateLeagueAlreadyExistsByNameAndCountry(List<League> leagues) {
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

    public List<League> updateLeagues(List<League> leagues) {
        validateLeagueCountry(leagues);
        validateLeagueAlreadyExistsByNameAndCountry(leagues);
        validateLeagueExistsById(leagues);
        return leagueRepository.saveAll(leagues);
    }

    private void validateLeagueExistsById(List<League> leagues) {
        leagues.forEach(league -> {
            if (!leagueRepository.existsById(league.getId())) {
                throw new CustomNotFoundException("League with id " + league.getId() + " not found.");
            }
        });
    }

    public League updateLeague(Long id, League league) {
        validateLeagueCountry(Collections.singletonList(league));
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