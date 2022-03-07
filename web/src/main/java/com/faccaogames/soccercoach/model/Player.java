package com.faccaogames.soccercoach.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Player {

    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private LocalDate dateOfBirth;
    private Skill skills;
    private Long teamId;
    private Integer age;
}