package ru.pet_project.weather_app.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Table(name = "city", schema = "public")
@Getter
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String state;
    private String country;
}
