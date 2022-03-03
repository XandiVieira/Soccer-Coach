package com.faccaogames.soccercoach.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Team {

    private Long id;
    private String name;
    private String country;
    private Long userId;
    private String imagePath;
    private Double money;
    private Set<Match> matches;
}