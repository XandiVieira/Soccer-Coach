package com.faccaogames.soccercoach.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Team {

    @Id
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private Long id;
    private String name;
    private String country;
    private Long userId;

    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Player> players;

    @JoinTable(name = "matches_teams",
            joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "match_id", referencedColumnName = "id"))
    @ManyToMany
    private Set<Match> matches;

    public Team(String name, String country, Long userId) {
        this.name = name;
        this.country = country;
        this.userId = userId;
    }

    public Team(String name, String country) {
        this.name = name;
        this.country = country;
    }
}