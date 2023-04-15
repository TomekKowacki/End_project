package com.end_project.api.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final WeatherConfig weatherConfig;
    private final RestTemplate restTemplate;

    public String getWeatherForecast() {

        URI url = UriComponentsBuilder
                .fromHttpUrl(weatherConfig.getWeatherApiEndPoint() + weatherConfig.getWeatherStationId())
                .build()
                .encode()
                .toUri();

        String forecast = restTemplate.getForObject(url, String.class);
        return forecast;
    }
}
