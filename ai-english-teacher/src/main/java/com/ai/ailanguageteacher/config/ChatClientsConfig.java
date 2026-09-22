package com.ai.ailanguageteacher.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientsConfig {

    @Bean("chatClientEnglishTeacher")
    public ChatClient chatClientTeacher(ChatClient.Builder chatClientBuilder,
                                        @Qualifier("chatOptionsEnglishTeacher") ChatOptions chatOptions) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        return chatClientBuilder
                .defaultAdvisors(List.of(loggerAdvisor))
                .defaultOptions(chatOptions)
                .build();
    }

    @Bean("chatClientVocabulary")
    public ChatClient chatClientVocabulary(ChatClient.Builder chatClientBuilder,
                                           @Qualifier("chatOptionsVocabulary") ChatOptions chatOptions) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        return chatClientBuilder
                .defaultAdvisors(List.of(loggerAdvisor))
                .defaultOptions(chatOptions)
                .build();
    }

    @Bean("chatClientTextEvaluator")
    public ChatClient chatClientTextEvaluator(ChatClient.Builder chatClientBuilder,
                                              @Qualifier("chatOptionsTextEvaluator") ChatOptions chatOptions) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        return chatClientBuilder
                .defaultAdvisors(List.of(loggerAdvisor))
                .defaultOptions(chatOptions)
                .build();
    }

    @Bean("chatOptionsVocabulary")
    public ChatOptions chatOptionsVocabulary() {
        return ChatOptions.builder()
                .maxTokens(1024)
                .temperature(0.6)
                .presencePenalty(0.3)
                .model("gpt-4o")
                .build();
    }

    @Bean("chatOptionsEnglishTeacher")
    public ChatOptions chatOptionsTeacher() {
        return ChatOptions.builder()
                .maxTokens(512)
                .temperature(0.3)
                .presencePenalty(0.2)
                .model("gpt-4o")
                .build();
    }

    @Bean("chatOptionsTextEvaluator")
    public ChatOptions chatOptionsTextEvaluator() {
        return ChatOptions.builder()
                .maxTokens(5120)
                .temperature(0.6)
                .presencePenalty(0.3)
                .model("gpt-4o")
                .build();
    }

}