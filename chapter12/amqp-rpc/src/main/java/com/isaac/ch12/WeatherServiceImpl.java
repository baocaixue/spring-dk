package com.isaac.ch12;

import org.springframework.stereotype.Component;

@Component
public class WeatherServiceImpl implements WeatherService {
    @Override
    public String getForecast(String stateCode) {
        if("FL".equals(stateCode)) {
            return "Hot";
        } else if("MA".equals(stateCode)) {
            return "Cold";
        }
        return "Not available at this time";
    }
}
