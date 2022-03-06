package com.faccaogames.soccercoach.controller;

import com.faccaogames.soccercoach.model.League;
import com.faccaogames.soccercoach.service.ContinentService;
import com.faccaogames.soccercoach.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/continent")
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    @GetMapping
    public List<String> getAllContinents() {
        return continentService.getAllContinents();
    }

}