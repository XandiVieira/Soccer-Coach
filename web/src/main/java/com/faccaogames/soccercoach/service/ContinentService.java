package com.faccaogames.soccercoach.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentService {

    private final FeignService feignService;

    public ContinentService(FeignService feignService) {
        this.feignService = feignService;
    }

    public List<String> getAllContinents() {
        return feignService.getContinents();
    }
}