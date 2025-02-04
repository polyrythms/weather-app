package ru.pet_project.weather_app.model;

import lombok.Data;

import java.util.Date;

@Data
public class Weather {
    City city;
    Date Date;
    Double temperature;
    Wind wind;
    Cloud cloud;
    Integer pressure;
    }
