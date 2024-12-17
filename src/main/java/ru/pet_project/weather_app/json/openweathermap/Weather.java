package ru.pet_project.weather_app.json.openweathermap;

import lombok.Data;

@Data
public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
}
