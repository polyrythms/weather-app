package ru.pet_project.weather_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Cloud {
    String description;
    Integer intensive;
}
