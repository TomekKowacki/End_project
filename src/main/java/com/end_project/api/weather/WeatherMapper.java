package com.end_project.api.weather;

import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {

    public WeatherDto mapToWeatherDto(Weather weather) {
        return new WeatherDto(weather.getStation(),
                weather.getDate(),
                weather.getTemperature(),
                weather.getPressure(),
                weather.getWind(),
                weather.getPrecipitation());
    }

    public Weather mapToWeather(WeatherDto weatherDto) {
        return new Weather(weatherDto.getStation(),
                weatherDto.getDate(),
                weatherDto.getTemperature(),
                weatherDto.getPressure(),
                weatherDto.getWind(),
                weatherDto.getPrecipitation());
    }
}
