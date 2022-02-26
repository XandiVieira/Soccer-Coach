package com.faccaogames.soccercoach.repository;

import com.faccaogames.soccercoach.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

}