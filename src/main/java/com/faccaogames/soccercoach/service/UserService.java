package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.exception.ApiRequestException;
import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.model.User;
import com.faccaogames.soccercoach.repository.UserRepository;
import com.faccaogames.soccercoach.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TeamService teamService;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, TeamService teamService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.teamService = teamService;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ApiRequestException("Nome de usuário já está sendo utilizado.");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCash(Constants.initialCash);
            user.setId(userRepository.save(user).getId());
            return user;
        }
    }

    public User retrieveUserById(Long id) {
        if (userRepository.existsById(id)) {
            return userRepository.getById(id);
        } else {
            throw new ApiRequestException("User with id " + id + " not found.");
        }
    }

    public User retrieveUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ApiRequestException("User with email " + email + " not found.");
        }
    }

    public Long updateUser(User user, Long id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user).getId();
        } else {
            throw new ApiRequestException("User with id " + id + " not found.");
        }
    }

    public String deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User " + id + " was deleted.";
        } else {
            throw new ApiRequestException("User with id " + id + " not found.");
        }
    }

    public void assignNewCoach(Long userId, Long teamId) {
        Team team = teamService.retrieveTeamById(teamId);
        team.setUserId(userId);
        teamService.updateTeam(teamId, team);

        User user = userRepository.getById(userId);
        user.setCurrentTeamId(team.getId());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ApiRequestException("Email not found."));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }
}