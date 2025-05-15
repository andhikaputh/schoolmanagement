package com.telu.schoolmanagement.common.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telu.schoolmanagement.common.constant.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisCacheUtil {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final AppConstant appConstant;

    public <T> void cacheValue(String key, T value) {
        try {
            String jsonValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, jsonValue, Duration.ofSeconds(appConstant.REDIS_TTL_IN_SECOND));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    public <T> T getCachedValue(String key, Class<T> className) {
        try {
            String redisData = redisTemplate.opsForValue().get(key);
            if (redisData != null) {
                return objectMapper.readValue(redisData, className);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize JSON to object", e);
        }
        return null;
    }

    public <T> T getCachedList(String key, TypeReference<T> typeRef) {
        try {
            String redisData = redisTemplate.opsForValue().get(key);
            if (redisData != null) {
                return objectMapper.readValue(redisData, typeRef);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize JSON to object", e);
        }
        return null;
    }

    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }
}
