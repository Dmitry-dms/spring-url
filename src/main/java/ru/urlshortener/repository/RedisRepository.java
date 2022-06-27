package ru.urlshortener.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class RedisRepository implements KVRepository {

    @Autowired
    private RedisTemplate<String, String> tmpl;

    @Override
    public void set(String key, String value, int ttl) {
        tmpl.opsForValue().set(key, value, Duration.ofSeconds(ttl));
    }

    @Override
    public String get(String key) {
        return tmpl.opsForValue().get(key);
    }
}