package com.statistic.util;

import com.common.dto.StatisticEventDto;
import com.statistic.interfaces.StatisticStrategy;
import com.statistic.service.UserStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuizStrategy implements StatisticStrategy {

    private final UserStatisticService userStatisticService;

    @Override
    public void resolve(StatisticEventDto statistic) {
        var userStatistic = userStatisticService.findByUserId(statistic.getUserId());
        userStatistic.setTotalCorrectQuizAnswers(userStatistic.getTotalCorrectQuizAnswers() + statistic.getCorrectCount());
        userStatistic.setTotalQuiz(userStatistic.getTotalQuiz() + statistic.getTotalItems());

        userStatisticService.save(userStatistic);
    }
}
