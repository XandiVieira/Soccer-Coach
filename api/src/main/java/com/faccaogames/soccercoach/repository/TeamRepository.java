package com.faccaogames.soccercoach.repository;

import com.faccaogames.soccercoach.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByNameAndCountry(String name, String country);

    List<Team> getByCountry(String country);
}