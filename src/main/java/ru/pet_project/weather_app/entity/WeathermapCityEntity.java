package ru.pet_project.weather_app.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "city_weathermap", schema = "public")
@Getter
public class WeathermapCityEntity {
    public WeathermapCityEntity() {

    }

    public WeathermapCityEntity(String city) {
        this.city = city;
    }
    @Id
    private Long id;
    private Long weathermapId;
    //    @Column(name = "city_id")
    //    private Long cityId;
    private String city;
    private String state;

    private String country;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityEntity cityEntity;
}
