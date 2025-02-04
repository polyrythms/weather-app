package ru.pet_project.weather_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wind {
    Double speed;
    Double gust;
    WindDirectionType direction;
}
