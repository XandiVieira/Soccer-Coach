package com.faccaogames.soccercoach.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class League {

    @Id
    @SequenceGenerator(
            name = "league_sequence",
            sequenceName = "league_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "league_sequence"
    )
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String country;
    @NotBlank
    private String logoPath;
}