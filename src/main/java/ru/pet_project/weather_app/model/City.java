package ru.pet_project.weather_app.model;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class City {
    private String city;
    private String country;
    private String state;

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
    public String toStringWithoutNullFields() {
        return city
                + (country == null ? "" : (", " + country))
                + (state == null ? "" : ", " + state);
    }
}
