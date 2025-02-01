package ru.pet_project.weather_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet_project.weather_app.entity.WeathermapCityEntity;

import java.util.List;

public interface WeathermapCityRepository extends JpaRepository<WeathermapCityEntity, Long> {
    List<WeathermapCityEntity> findAllByCityEntityCity(String city);

    List<WeathermapCityEntity> findAllByCityEntityCityAndCountry(String city, String country);

    List<WeathermapCityEntity> findAllByCityEntityCityAndCountryAndState(String city, String country, String state);
}
