package ru.pet_project.weather_app.json.openweathermap;

import lombok.Data;

@Data
public class City {
    private Integer id;
    private String name;
    private Coord coord;
    private String country;
    private Integer population;
    private Integer timezone;
    private Long sunrise;
    private Long sunset;
}
