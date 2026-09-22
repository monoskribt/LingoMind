package com.ai.ailanguageteacher.handler;

import com.ai.ailanguageteacher.dto.session.ProgressSessionDto;
import com.ai.ailanguageteacher.dto.session.QuizSession;
import com.ai.ailanguageteacher.service.VocabularyCacheManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

import static com.ai.ailanguageteacher.util.CacheUtils.QUIZ_CACHE;
import static com.ai.ailanguageteacher.util.CacheUtils.QUIZ_CACHE_KEY;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class QuizWebSocketHandler extends TextWebSocketHandler {

    private final VocabularyCacheManager vocabularyCacheManager;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        var userId = (Long) session.getAttributes().get("userId");
        var answer = mapper.readValue(message.getPayload(), ProgressSessionDto.class);

        vocabularyCacheManager.update(QUIZ_CACHE, QUIZ_CACHE_KEY.formatted(userId),
                QuizSession.class, quiz -> {
                    quiz.setCurrentIndex(answer.getCurrentIndex());
                    if (answer.isCorrect()) {
                        quiz.setCorrect(quiz.getCorrect() + 1);
                    } else {
                        quiz.setIncorrect(quiz.getIncorrect() + 1);
                    }
                });
    }
}
