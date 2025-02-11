package com.ambro.cache.service.service;

import com.ambro.cache.service.entity.Weather;
import com.ambro.cache.service.repository.WeatherRepository;
import jakarta.transaction.Transactional;
import org.json.simple.JSONObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    WeatherRepository repo;
    public WeatherService(WeatherRepository repo) {
        this.repo = repo;
    }

    public JSONObject addWeather(Weather weather){
        JSONObject res = new JSONObject();
        Optional<Weather> weatherOptional = Optional.ofNullable(repo.save(weather));
        if(weatherOptional.isPresent()){
            res.put("Status", "Ok");
            res.put("Details", "Data saved");
        }else{
            res.put("Status", "Failed");
        }
        return res;
    }

    @Cacheable(value = "weather", key = "#city")
    public Weather getWeather(String city){
        Optional<Weather> weatherOptional = repo.findById(city);
        if(weatherOptional.isPresent()){
            return weatherOptional.get();
        }
        return null;
    }

    @CachePut(value = "weather", key = "#city")
    public Weather updateWeather(String city, String weather){
        Weather res = new Weather();
        Optional<Weather> weatherOptional = repo.findById(city);
        if(weatherOptional.isPresent()){
            res = weatherOptional.get();
            res.setWeather(weather);
            return res;
        }
        return null;
    }

    @Transactional
    @CacheEvict(value = "weather", key = "#city")
    public void deleteWeather(String city){
        Optional<Weather> weatherOptional = repo.findById(city);
        if(weatherOptional.isPresent()){
            Weather weatherObj = weatherOptional.get();
            repo.deleteById(weatherObj.getCity());
        }
    }

    public List<Weather> getAll(){
        return repo.findAll();
    }



}
