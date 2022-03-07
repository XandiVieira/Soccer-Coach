package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.exception.CustomAlreadyExistsException;
import com.faccaogames.soccercoach.exception.CustomNotFoundException;
import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.model.User;
import com.faccaogames.soccercoach.repository.UserRepository;
import com.faccaogames.soccercoach.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TeamService teamService;

    @Autowired
    public UserService(UserRepository userRepository, TeamService teamService) {
        this.userRepository = userRepository;
        this.teamService = teamService;
    }

    public User createUser(User user) {
        validateUsernameAlreadyExists(user.getUsername());

        user.setPassword(user.getPassword());
        user.setCash(Constants.initialCash);
        user.setId(userRepository.save(user).getId());
        return user;
    }

    private void validateUsernameAlreadyExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new CustomAlreadyExistsException("Username " + username + " already taken.");
        }
    }

    public List<User> getAllUsers() {
        if (userRepository.count() > 0) {
            return userRepository.findAll();
        } else {
            throw new CustomNotFoundException("No users were found.");
        }
    }

    public User getUserById(Long id) {
        if (userRepository.existsById(id)) {
            return userRepository.getById(id);
        } else {
            throw new CustomNotFoundException("User with id " + id + " not found.");
        }
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new CustomNotFoundException("User with email " + email + " not found.");
        }
    }

    public Long updateUser(User user, Long id) {
        validateUsernameAlreadyExists(user.getUsername());
        validateUserExistsById(id);
        user.setId(id);
        return userRepository.save(user).getId();
    }

    private void validateUserExistsById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new CustomNotFoundException("User with id " + id + " not found.");
        }
    }

    public String deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User " + id + " was deleted.";
        } else {
            throw new CustomNotFoundException("User with id " + id + " not found.");
        }
    }

    public void assignNewCoach(Long userId, Long teamId) {
        Team team = teamService.getTeamById(teamId);
        team.setUserId(userId);
        teamService.updateTeam(teamId, team);

        User user = userRepository.getById(userId);
        user.setCurrentTeamId(team.getId());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomNotFoundException("Email not found."));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }
}