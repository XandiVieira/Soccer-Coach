package com.faccaogames.soccercoach.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    private Long id;
    private Long homeTeamId;
    private Long awayTeamId;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
}