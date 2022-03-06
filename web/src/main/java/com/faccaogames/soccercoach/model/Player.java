package com.faccaogames.soccercoach.model;

import java.time.LocalDate;

public class Player extends BaseModel {

    private String firstName;
    private String lastName;
    private String position;
    private LocalDate dateOfBirth;
    private Skill skills;
    private Long teamId;
    private Integer age;
}