package com.faccaogames.soccercoach.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Player {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @NotBlank
    private String position;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotBlank
    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    private Skill skills;

    @NotBlank
    private Long teamId;

    @Transient
    private Integer age;

    public Player(String firstName, String lastName, String position, LocalDate dateOfBirth, Long teamId, Skill skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.teamId = teamId;
        this.skills = skills;
    }

    public Player(String firstName, String lastName, String position, LocalDate dateOfBirth, Skill skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.skills = skills;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}