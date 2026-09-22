package com.ai.ailanguageteacher.service;

import com.ai.ailanguageteacher.dto.response.VocabularySessionFinishResponse;
import com.ai.ailanguageteacher.dto.session.QuizSession;
import com.ai.ailanguageteacher.dto.session.SentenceGapSession;
import com.ai.ailanguageteacher.exception.ApiValidationException;
import com.ai.ailanguageteacher.util.CacheUtils;
import com.common.config.RabbitSettings;
import com.common.dto.StatisticEventDto;
import com.common.dto.enums.AttemptType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VocabularySessionService {

    private final VocabularyCacheManager cacheManager;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitSettings rabbitSettings;

    public QuizSession getQuizSession(Long userId) {
        var session = cacheManager.get(CacheUtils.QUIZ_CACHE, CacheUtils.QUIZ_CACHE_KEY.formatted(userId),
                QuizSession.class);

        if (session == null) {
            throw new ApiValidationException("Quiz Session is not present yet");
        }

        return session;
    }

    public SentenceGapSession getSentenceGapSession(Long userId) {
        var session = cacheManager.get(CacheUtils.SENTENCE_GAP_CACHE, CacheUtils.SENTENCE_GAP_CACHE_KEY.formatted(userId),
                SentenceGapSession.class);

        if (session == null) {
            throw new ApiValidationException("Sentence Gap Session is not present yet");
        }

        return session;
    }

    public VocabularySessionFinishResponse quizSessionFinish(Long userId) {
        var session = cacheManager.get(CacheUtils.QUIZ_CACHE, CacheUtils.QUIZ_CACHE_KEY.formatted(userId),
                QuizSession.class);

        if (session == null) {
            throw new ApiValidationException("Quiz Session is not present yet");
        }

        cacheManager.delete(CacheUtils.QUIZ_CACHE, CacheUtils.QUIZ_CACHE_KEY.formatted(userId));
        rabbitTemplate.convertAndSend(rabbitSettings.getExchange(), rabbitSettings.getKey(),
                new StatisticEventDto(userId, AttemptType.QUIZLET, session.getLevel(), session.getCorrect(),
                        session.getCorrect() + session.getIncorrect()));
        return new VocabularySessionFinishResponse(session.getCorrect(), session.getCorrect() + session.getIncorrect());
    }

    public VocabularySessionFinishResponse sentenceGapSessionFinish(Long userId) {
        var session = cacheManager.get(CacheUtils.SENTENCE_GAP_CACHE, CacheUtils.SENTENCE_GAP_CACHE_KEY.formatted(userId),
                SentenceGapSession.class);

        if (session == null) {
            throw new ApiValidationException("Sentence Gap Session is not present yet");
        }

        cacheManager.delete(CacheUtils.SENTENCE_GAP_CACHE, CacheUtils.SENTENCE_GAP_CACHE_KEY.formatted(userId));
        rabbitTemplate.convertAndSend(rabbitSettings.getExchange(), rabbitSettings.getKey(),
                new StatisticEventDto(userId, AttemptType.SENTENCE, session.getLevel(), session.getCorrect(),
                        session.getCorrect() + session.getIncorrect()));
        return new VocabularySessionFinishResponse(session.getCorrect(), session.getCorrect() + session.getIncorrect());
    }
}
