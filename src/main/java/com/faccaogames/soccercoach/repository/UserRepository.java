package com.faccaogames.soccercoach.repository;

import com.faccaogames.soccercoach.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    Boolean existsByUsername(String username);

    List<User> findUserByUsername(String username);
}