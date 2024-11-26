package ru.pet_project.weather_app.service;

import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.model.Weather;

public interface WeatherSupplier {
    public Weather getWeather(City city);
}
