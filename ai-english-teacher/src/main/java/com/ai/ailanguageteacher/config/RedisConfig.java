package com.ai.ailanguageteacher.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

import static com.ai.ailanguageteacher.util.CacheUtils.QUIZ_CACHE;
import static com.ai.ailanguageteacher.util.CacheUtils.SENTENCE_GAP_CACHE;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisSettings settings;

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration(QUIZ_CACHE,
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(settings.getQuizCache().getTtl())))
                .withCacheConfiguration(SENTENCE_GAP_CACHE,
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(settings.getSentenceGapCache().getTtl())));
    }
}

