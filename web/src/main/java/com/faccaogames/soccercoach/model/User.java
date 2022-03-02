package com.faccaogames.soccercoach.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    @NotBlank(message = "email must be informed.")
    @Email
    private String email;

    @NotBlank(message = "password must be informed.")
    private String password;

    @NotBlank(message = "username must be informed.")
    private String username;

    private Long currentTeamId;
    private Double cash;

    public User(String email, String password, String username, Double cash) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.cash = cash;
    }
}