package com.faccaogames.soccercoach.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@Data
@Entity
@Table
public class Player {

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
    private String firstName;
    private String lastName;
    private String position;
    private LocalDate dateOfBirth;
    //private Skill skills;
    private Long teamId;

    @Transient
    private Integer age;

    public Player(String firstName, String lastName, String position, LocalDate dateOfBirth, Long teamId, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.teamId = teamId;
        this.age = age;
    }

    public Player(String firstName, String lastName, String position, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}