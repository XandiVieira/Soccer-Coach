package com.faccaogames.soccercoach.model;

import java.math.BigDecimal;
import java.util.Set;

public class Team extends BaseModel {

    private String name;
    private String country;
    private Long userId;
    private String imagePath;
    private BigDecimal money;
    private Set<Match> matches;
}