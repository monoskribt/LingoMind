package com.ai.ailanguageteacher.config;

import com.ai.ailanguageteacher.handler.QuizWebSocketHandler;
import com.ai.ailanguageteacher.handler.SentenceGapWebSocketHandler;
import com.ai.ailanguageteacher.interceptors.UserIdHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final QuizWebSocketHandler quizWebSocketHandler;
    private final SentenceGapWebSocketHandler sentenceGapWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(quizWebSocketHandler, "/ws/quiz/{userId}")
                .addInterceptors(new UserIdHandshakeInterceptor())
                .setAllowedOrigins("*");

        registry.addHandler(sentenceGapWebSocketHandler, "/ws/sentence-gap/{userId}")
                .addInterceptors(new UserIdHandshakeInterceptor())
                .setAllowedOrigins("*");
    }
}
