package com.faccaogames.soccercoach.repository;

import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

}