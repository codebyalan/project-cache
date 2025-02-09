package com.ambro.cache.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheServiceApplication.class);
    }
}