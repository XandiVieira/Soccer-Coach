package com.faccaogames.soccercoach.service.LeagueServiceTest;

import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.repository.LeagueRepository;
import com.faccaogames.soccercoach.service.LeagueService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class LeagueServiceTest {

    @MockBean
    private LeagueRepository leagueRepository;

    @InjectMocks
    private LeagueService leagueService;

    @Test
    public void shouldCreateLeagues() {
        when(this.leagueService.createLeagues(generateLeagues()));
    }

    private List<League> generateLeagues() {
        List<League> leagues = new ArrayList<>();
        League league = new League();
        league.setName("Brasileirao");
        league.setCountry("Brasil");
        league.setLogoPath("brasileirao.png");
        League league2 = new League();
        league2.setName("Premier League");
        league2.setCountry("Inglaterra");
        league2.setLogoPath("premier_league.png");
        leagues.add(league);
        leagues.add(league2);
        return leagues;
    }
}
