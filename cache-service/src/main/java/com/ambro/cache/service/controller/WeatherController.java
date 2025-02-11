package com.ambro.cache.service.controller;

import com.ambro.cache.service.entity.Weather;
import com.ambro.cache.service.service.CacheService;
import com.ambro.cache.service.service.WeatherService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class WeatherController {

    WeatherService service;
    CacheService cacheService;

    public WeatherController(CacheService cacheService, WeatherService service) {
        this.cacheService = cacheService;
        this.service = service;
    }

    @GetMapping
    public Weather get(@RequestParam("city") String city){
        return service.getWeather(city);
    }

    @PostMapping
    public JSONObject add(@RequestParam("city") String city, @RequestParam("weather") String weather){
        return service.addWeather(new Weather(city, weather));
    }

    @PutMapping
    public Weather update(@RequestParam("city") String city, @RequestParam("weather") String weather){
        return service.updateWeather(city, weather);
    }

    @DeleteMapping
    public void delete(@RequestParam("city") String city){
        service.deleteWeather(city);
    }

    @GetMapping("getAll")
    public List<Weather> getAll(){
        return service.getAll();
    }

    @GetMapping("cache")
    public JSONObject cache(@RequestParam("weather") String cache){
        return cacheService.getCacheDetails(cache);
    }

}
