package com.faccaogames.soccercoach.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Skill {

    @Id
    @SequenceGenerator(
            name = "skill_sequence",
            sequenceName = "skill_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "skill_sequence"
    )
    private Long id;
    private Integer defense;
    private Integer attack;
    private Integer pass;
    private Integer shot;
}
