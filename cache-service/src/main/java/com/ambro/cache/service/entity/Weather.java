package com.ambro.cache.service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @Column(name = "city", nullable = false, unique = true)
    String city;

    @Column(name = "weather", nullable = false)
    String weather;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Weather(String city, String weather) {
        this.city = city;
        this.weather = weather;
    }

    public Weather() {
    }
}
