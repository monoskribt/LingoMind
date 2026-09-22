package com.ai.ailanguageteacher.handler;

import com.ai.ailanguageteacher.dto.session.ProgressSessionDto;
import com.ai.ailanguageteacher.dto.session.QuizSession;
import com.ai.ailanguageteacher.dto.session.SentenceGapSession;
import com.ai.ailanguageteacher.service.VocabularyCacheManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

import static com.ai.ailanguageteacher.util.CacheUtils.*;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class SentenceGapWebSocketHandler extends TextWebSocketHandler {

    private final VocabularyCacheManager vocabularyCacheManager;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        var userId = (Long) session.getAttributes().get("userId");
        var answer = mapper.readValue(message.getPayload(), ProgressSessionDto.class);

        vocabularyCacheManager.update(SENTENCE_GAP_CACHE, SENTENCE_GAP_CACHE_KEY.formatted(userId),
                SentenceGapSession.class, gap -> {
                    gap.setCurrentIndex(answer.getCurrentIndex());
                    if (answer.isCorrect()) {
                        gap.setCorrect(gap.getCorrect() + 1);
                    } else {
                        gap.setIncorrect(gap.getIncorrect() + 1);
                    }
                });
    }
}
