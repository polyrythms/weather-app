package ru.pet_project.weather_app.json.openweathermap;

import lombok.Data;

import java.util.List;

@Data
public class Forecast {
    private Long dt;
    private Main main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private Integer visibility;
    private Double pop;
    private Rain rain;
    private Sys sys;
    private String dt_txt;
}
