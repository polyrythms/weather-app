package ru.pet_project.weather_app.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "user", schema = "public")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long chatId;
    private String name;
}
