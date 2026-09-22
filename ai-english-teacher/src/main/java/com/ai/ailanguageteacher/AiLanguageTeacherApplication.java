package com.ai.ailanguageteacher;

import com.ai.ailanguageteacher.config.RedisSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.ai.ailanguageteacher", "com.common"})
@EnableConfigurationProperties(value = RedisSettings.class)
public class AiLanguageTeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiLanguageTeacherApplication.class, args);
    }

}
