package ru.pet_project.weather_app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pet_project.weather_app.entity.WeathermapCityEntity;
import ru.pet_project.weather_app.json.openweathermap.Forecast;
import ru.pet_project.weather_app.json.openweathermap.OpenweathermapResponse;
import ru.pet_project.weather_app.model.*;
import ru.pet_project.weather_app.repository.WeathermapCityRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class WeathermapService {
    private final WeathermapClient weathermapClient;
    private final WeathermapCityRepository weathermapCityRepository;

    public List<WeathermapCityEntity> validateCity(City city) {
        if (city.getCity() != null && city.getCountry() == null && city.getState() == null) {
            return weathermapCityRepository.findAllByCityEntityCity(city.getCity());
        } else if ((city.getCity() != null && city.getCountry() != null && city.getState() == null)) {
            return weathermapCityRepository.findAllByCityEntityCityAndCityEntityCountry(city.getCity(), city.getCountry());
        } else if (city.getCity() != null && city.getCountry() != null && city.getState() != null) {
            return weathermapCityRepository.findByCityEntityCityAndCityEntityCountryAndCityEntityState(city.getCity(), city.getCountry(), city.getState());
        } else {
            return Collections.emptyList();
        }
    }

    public List<Weather> getWeather(WeathermapCityEntity entity) {
        OpenweathermapResponse
                response = weathermapClient.getWeather(entity.getWeathermapId()).block();
        return convertWmResponseToWeather(response, entity);
    }

    private List<Weather> convertWmResponseToWeather(OpenweathermapResponse response, WeathermapCityEntity entity) {
        List<Weather> result = new ArrayList<>();
        for (Forecast wmForecast : response.getList()) {
            Weather weather = new Weather();
            weather.setCity(new City(entity.getCityEntity().getCity(), entity.getCityEntity().getCountry(), entity.getCityEntity().getState()));
            weather.setDate(new Date((long) wmForecast.getDt() * 1000));
            weather.setWind(new Wind(
                    wmForecast.getWind().getSpeed(),
                    wmForecast.getWind().getGust(),
                    WindDirectionType.getTypeByDegree(wmForecast.getWind().getDeg())));
            weather.setTemperature(wmForecast.getMain().getTemp());
            weather.setPressure(wmForecast.getMain().getPressure());
            weather.setCloud(new Cloud(wmForecast.getWeather().get(0).getDescription(), wmForecast.getClouds().getAll()));
            result.add(weather);
        }
        return result;
        //    Forecast(dt=1738659600, main=Main(temp=0.4, feelsLike=-1.95, tempMin=0.4, tempMax=2.65, pressure=1028, seaLevel=1028, grndLevel=1018, humidity=90, tempKf=-2.25), weather=[Weather(id=803, main=Clouds, description=broken clouds, icon=04d)], clouds=Clouds(all=79), wind=Wind(speed=1.97, deg=175, gust=4.81), visibility=10000, pop=0.0, rain=null, sys=Sys(pod=d), dt_txt=2025-02-04 09:00:00)
    }
}
