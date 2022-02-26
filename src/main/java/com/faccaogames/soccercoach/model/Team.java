package com.faccaogames.soccercoach.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String name;
    @NotBlank
    private String country;
    private Long userId;
    @NotBlank
    private String imagePath;

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

    public Team(String name, String country, String imagePath) {
        this.name = name;
        this.country = country;
        this.imagePath = imagePath;
    }
}