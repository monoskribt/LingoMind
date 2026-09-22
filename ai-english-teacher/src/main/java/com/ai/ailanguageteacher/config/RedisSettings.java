package com.ai.ailanguageteacher.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "redis")
public class RedisSettings {

    private QuizCache quizCache;
    private SentenceGapCache sentenceGapCache;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuizCache {
        private Long ttl;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SentenceGapCache {
        private Long ttl;
    }
}
