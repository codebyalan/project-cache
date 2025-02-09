package com.ambro.cache.service.service;

import com.ambro.cache.service.entity.Weather;
import com.ambro.cache.service.repository.WeatherRepository;
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
    public JSONObject getWeather(String city){
        JSONObject res = new JSONObject();

        Optional<Weather> weatherOptional = repo.findById(city);
        if(weatherOptional.isPresent()){
            res.put("city", weatherOptional.get().getCity());
            res.put("weather", weatherOptional.get().getWeather());
        }else{
            res.put("Status", "Not found");
        }
        return res;
    }

    @CachePut(value = "weather", key = "#city")
    public JSONObject updateWeather(String city, String weather){
        JSONObject res = new JSONObject();

        Optional<Weather> weatherOptional = repo.findById(city);
        if(weatherOptional.isPresent()){
            Weather weatherObj = weatherOptional.get();
            weatherObj.setWeather(weather);
            repo.save(weatherObj);
            res.put("city", weatherObj.getCity());
            res.put("weather", weatherObj.getWeather());
            res.put("Status", city + " weather updated");
        }else{
            res.put("Status", "Not found");
        }
        return res;
    }

    @CacheEvict(value = "weather", key = "#city")
    public JSONObject deleteWeather(String city){
        JSONObject res = new JSONObject();

        Optional<Weather> weatherOptional = repo.findById(city);
        if(weatherOptional.isPresent()){
            Weather weatherObj = weatherOptional.get();
            repo.delete(weatherObj);
            res.put("city", weatherObj.getCity());
            res.put("Status", "City deleted");
        }else{
            res.put("Status", "Not found");
        }
        return res;
    }

    public List<Weather> getAll(){
        return repo.findAll();
    }



}
