package ru.pet_project.weather_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet_project.weather_app.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
