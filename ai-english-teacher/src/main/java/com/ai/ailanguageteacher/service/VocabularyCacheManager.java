package com.ai.ailanguageteacher.service;

import com.ai.ailanguageteacher.dto.session.CacheDto;
import com.ai.ailanguageteacher.exception.ApiValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class VocabularyCacheManager {

    private final CacheManager cacheManager;

    public void save(String cacheName, String key, CacheDto session) {
        cacheManager.getCache(cacheName).put(key, session);
    }

    public <T extends CacheDto> T get(String cacheName, String key, Class<T> type) {
        var wrapper = cacheManager.getCache(cacheName).get(key);

        if (wrapper == null) {
            return null;
        }

        return type.cast(wrapper.get());
    }

    public <T extends CacheDto> void update(String cacheName, String key, Class<T> type, Consumer<T> updater) {
        var session = get(cacheName, key, type);

        if (session == null) {
            throw new ApiValidationException("Session not found for userId: %s"
                    .formatted(key.substring(key.indexOf(":") + 1)));
        }

        updater.accept(session);
        save(cacheName, key, session);
    }

    public void delete(String cacheName, String key) {
        cacheManager.getCache(cacheName).evict(key);
    }
}
