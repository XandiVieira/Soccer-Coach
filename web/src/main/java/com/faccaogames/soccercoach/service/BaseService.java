package com.faccaogames.soccercoach.service;

import org.springframework.stereotype.Service;

@Service
public abstract class BaseService {

    public final FeignService feignService;

    public BaseService(FeignService feignService) {
        this.feignService = feignService;
    }
}