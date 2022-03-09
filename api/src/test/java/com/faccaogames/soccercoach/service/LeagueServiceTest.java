package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.exception.CustomAlreadyExistsException;
import com.faccaogames.soccercoach.exception.CustomNotFoundException;
import com.faccaogames.soccercoach.exception.CustomNotValidException;
import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.repository.LeagueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeagueServiceTest {

    @InjectMocks
    private LeagueService leagueService;

    @Mock
    private LeagueRepository repository;

    @Test
    void createLeagues() {
        List<League> leagues = createListOfLeagues();

        leagueService.createLeagues(leagues);

        verify(repository, times(1)).saveAll(leagues);
    }

    @Test
    void createLeaguesShouldThrowNotValidExceptionForInvalidCountry() {
        String country = "China";
        List<League> leagues = createListOfLeagues().stream().peek(league -> league.setCountry(country)
        ).collect(Collectors.toList());

        assertThatThrownBy(() -> leagueService.createLeagues(leagues))
                .isInstanceOf(CustomNotValidException.class)
                .hasMessageContaining("Country " + country + " is invalid.");

        verify(repository, never()).saveAll(leagues);
    }

    @Test
    void createLeaguesShouldThrowNotValidExceptionForInvalidContinent() {
        String continent = "South America";
        List<League> leagues = createListOfLeagues().stream().peek(league -> league.setContinent(continent)
        ).collect(Collectors.toList());

        assertThatThrownBy(() -> leagueService.createLeagues(leagues))
                .isInstanceOf(CustomNotValidException.class)
                .hasMessageContaining("Continent " + continent + " is invalid.");

        verify(repository, never()).saveAll(leagues);
    }

    @Test
    void createLeaguesShouldThrowAlreadyExistsExceptionForNameAndCountry() {
        League league = createSingleLeague();

        when(repository.existsByNameAndCountry(league.getName(), league.getCountry())).thenReturn(true);

        assertThatThrownBy(() -> leagueService.createLeagues(Collections.singletonList(league)))
                .isInstanceOf(CustomAlreadyExistsException.class)
                .hasMessageContaining("League " + league.getName() + " already exists in " + league.getCountry());

        verify(repository, times(1)).existsByNameAndCountry(anyString(), anyString());
    }

    @Test
    void getAllLeagues() {
        List<League> existingLeagues = createListOfLeagues();

        when(repository.findAll()).thenReturn(existingLeagues);

        List<League> leagues = leagueService.getAllLeagues();

        assertEquals(4, leagues.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void getLeagueById() {
        long id = 1L;
        League existingLeague = createSingleLeague();

        when(repository.getById(id)).thenReturn(existingLeague);
        when(repository.existsById(id)).thenReturn(true);

        League league = leagueService.getLeagueById(id);

        assertEquals(league.getId(), existingLeague.getId());
        assertEquals(league.getContinent(), existingLeague.getContinent());
        assertEquals(league.getCountry(), existingLeague.getCountry());
        assertEquals(league.getName(), existingLeague.getName());
        assertEquals(league.getLogoPath(), existingLeague.getLogoPath());

        verify(repository, times(1)).getById(id);
    }

    @Test
    void getLeagueByIdShouldThrowNotFoundException() {
        long id = 1L;

        when(repository.existsById(id)).thenReturn(false);

        assertThatThrownBy(() ->
                leagueService.getLeagueById(id))
                .isInstanceOf(CustomNotFoundException.class)
                .hasMessageContaining("League with id " + id + " not found.");

        verify(repository, never()).getById(anyLong());
    }

    @Test
    void getLeagueByContinent() {
        String continent = "Europe";

        List<League> europeanLeagues = createListOfLeagues().stream().filter(league -> league.getContinent().equals(continent)).toList();

        when(repository.findByContinent(continent)).thenReturn(europeanLeagues);
        when(repository.existsByContinent(continent)).thenReturn(true);

        List<League> leagues = leagueService.getLeaguesByContinent(continent);

        assertEquals(3, leagues.size());

        verify(repository, times(1)).findByContinent(continent);
    }

    @Test
    void getLeagueByContinentShouldThrowNotFoundException() {
        String continent = "Europe";

        when(repository.existsByContinent(continent)).thenReturn(false);

        assertThatThrownBy(() -> leagueService.getLeaguesByContinent(continent)).isInstanceOf(CustomNotFoundException.class).hasMessageContaining("League with continent " + continent + " not found.");

        verify(repository, never()).findByContinent(continent);
    }

    @Test
    void updateLeagues() {
        List<League> leagues = createListOfLeagues().stream().peek(league -> league.setName(league.getName().toUpperCase())).toList();

        when(repository.existsById(anyLong())).thenReturn(true);

        leagueService.updateLeagues(leagues);

        verify(repository, times(1)).saveAll(anyList());
    }

    @Test
    void updateLeaguesShouldThrowNotFoundExceptionForNonExistingLeague() {
        List<League> leagues = createListOfLeagues().stream().peek(league -> league.setName(league.getName().toUpperCase())).toList();

        when(repository.existsById(anyLong())).thenReturn(false);

        assertThatThrownBy(() -> leagueService.updateLeagues(leagues))
                .isInstanceOf(CustomNotFoundException.class)
                .hasMessageContaining(" not found.");

        verify(repository, never()).saveAll(anyList());
    }

    @Test
    void updateLeague() {
        League league = createSingleLeague();

        when(repository.existsById(anyLong())).thenReturn(true);
        leagueService.updateLeague(league.getId(), league);

        verify(repository, times(1)).save(league);
    }

    @Test
    void updateLeagueShouldThrowNotFoundExceptionForNonExistingLeague() {
        League league = createSingleLeague();

        when(repository.existsById(anyLong())).thenReturn(false);

        assertThatThrownBy(() -> leagueService.updateLeague(league.getId(), league))
                .isInstanceOf(CustomNotFoundException.class)
                .hasMessageContaining(" not found.");

        verify(repository, never()).save(league);
    }

    @Test
    void deleteLeague() {
        League league = createSingleLeague();

        when(repository.existsById(league.getId())).thenReturn(true);

        leagueService.deleteLeague(league.getId());

        verify(repository, times(1)).deleteById(league.getId());
    }

    @Test
    void deleteLeagueShouldThrowNotFoundExceptionForNonExistingLeague() {
        League league = createSingleLeague();

        when(repository.existsById(league.getId())).thenReturn(false);

        assertThatThrownBy(() -> leagueService.deleteLeague(league.getId()))
                .isInstanceOf(CustomNotFoundException.class)
                .hasMessageContaining("League with id " + league.getId() + " not found.");

        verify(repository, never()).deleteById(league.getId());
    }

    private List<League> createListOfLeagues() {
        List<League> leagues = new ArrayList<>();
        leagues.add(new League(1L, "Brasileirão", "Brazil", "America", "brasileirao.png"));
        leagues.add(new League(2L, "Premier League", "England", "Europe", "premier.png"));
        leagues.add(new League(3L, "Bundesliga", "Germany", "Europe", "bundes.png"));
        leagues.add(new League(4L, "Série A", "Italy", "Europe", "seriea.png"));
        return leagues;
    }

    private League createSingleLeague() {
        return createListOfLeagues().get(0);
    }
}