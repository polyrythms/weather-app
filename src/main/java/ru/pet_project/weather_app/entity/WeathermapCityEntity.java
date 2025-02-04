package ru.pet_project.weather_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "city_weathermap", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class WeathermapCityEntity {
    public WeathermapCityEntity(String city) {
        this.city = city;
    }
    @Id
    private Long id;
    @Column(name = "weathermap_id")
    private Long weathermapId;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityEntity cityEntity;
}
