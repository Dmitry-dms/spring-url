package ru.urlshortener.repository;

public interface KVRepository {
    void set(String key, String value, int ttl);
    String get(String key);
}
