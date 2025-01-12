package ru.pet_project.weather_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet_project.weather_app.entity.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
}
