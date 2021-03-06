package com.faccaogames.soccercoach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.faccaogames.soccercoach.model.League;

import java.util.List;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

    boolean existsByNameAndCountry(String name, String country);

    boolean existsByContinent(String continent);

    List<League> findByContinent(String continent);
}