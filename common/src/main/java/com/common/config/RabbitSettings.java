package com.common.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbit")
@Getter
@Setter
public class RabbitSettings {

    private String queue;
    private String exchange;
    private String key;

    private String dlq;
    private String exchangeDlq;
    private String keyDlq;

    private Config config;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Config {

        private Long delay;
        private Integer retryBackoff;
        private Integer numberAttempts;

    }

}
