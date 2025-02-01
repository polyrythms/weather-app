package ru.pet_project.weather_app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class City {
    private String city;
    private String country;
    private String state;
}
