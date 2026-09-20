package com.statistic.util;

import com.common.dto.enums.AttemptType;
import com.statistic.interfaces.StatisticStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatisticStrategyResolver {

    private final QuizStrategy quizStrategy;
    private final SentenceStrategy sentenceStrategy;

    public StatisticStrategy resolveStrategy(AttemptType type) {
        return switch (type) {
            case QUIZLET -> quizStrategy;
            case SENTENCE -> sentenceStrategy;
        };
    }
}
