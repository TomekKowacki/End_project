package com.end_project.api.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherDto {

    private String station;
    private String date;
    private String temperature;
    private String pressure;
    private String wind;
    private String precipitation;
}
