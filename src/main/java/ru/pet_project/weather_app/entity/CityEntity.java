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

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
    public String toStringWithoutNullFields() {
        return city
                + (country == null ? "" : (", " + country))
                + (state == null ? "" : ", " + state);
    }
}
