package com.end_project.api.weather;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WeatherConfig {

    @Value("https://danepubliczne.imgw.pl/api/data/synop/id/")
    private String weatherApiEndPoint;

    @Value("12566")
    private String weatherStationId;
}
