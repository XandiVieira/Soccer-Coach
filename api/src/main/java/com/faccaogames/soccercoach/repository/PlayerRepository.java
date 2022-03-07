package com.faccaogames.soccercoach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.faccaogames.soccercoach.model.Player;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByTeamId(Long teamId);

    List<Player> getByTeamId(Long teamId);
}