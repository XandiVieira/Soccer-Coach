package com.faccaogames.soccercoach.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
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
    private Integer age;
    private LocalDate dateOfBirth;
    //private Skill skills;
    private Long teamId;

    public Player(String firstName, String lastName, String position, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.age = age;
    }
}
