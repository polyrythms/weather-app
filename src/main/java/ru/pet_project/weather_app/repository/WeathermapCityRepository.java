package ru.pet_project.weather_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet_project.weather_app.entity.WeathermapCityEntity;

import java.util.List;

public interface WeathermapCityRepository extends JpaRepository<WeathermapCityEntity, Long> {
    List<WeathermapCityEntity> findByCity(String city);
}
