package com.faccaogames.soccercoach.service;

import com.faccaogames.soccercoach.model.enums.Continent;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContinentService {

    public List<String> getAllContinents() {
        return FormatStringName(Arrays.stream(Continent.values()).map(Enum::name).collect(Collectors.toList()));
    }

    private List<String> FormatStringName(List<String> collect) {
        return collect.stream().map(StringUtils::capitalize).collect(Collectors.toList());
    }
}