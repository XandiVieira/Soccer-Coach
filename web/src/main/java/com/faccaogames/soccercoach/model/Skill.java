package com.faccaogames.soccercoach.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    private Long id;
    private Integer defense;
    private Integer attack;
    private Integer pass;
    private Integer shot;
}