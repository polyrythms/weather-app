package ru.pet_project.weather_app.json.openweathermap;

import lombok.Data;

@Data
public class Wind {
    private Double speed;
    private Integer deg;
    private Double gust;
}
