package com.ambro.cache.service.service;

import org.json.simple.JSONObject;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheService {

    CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public JSONObject getCacheDetails(String cacheName){
        JSONObject jsonObject = new JSONObject();
        try{
            Cache cache = cacheManager.getCache(cacheName);
            jsonObject.put("Cache content:", Objects.requireNonNull(cache.getNativeCache()).toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

}
