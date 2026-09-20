package com.statistic.util;

import com.common.dto.StatisticEventDto;
import com.statistic.interfaces.StatisticStrategy;
import com.statistic.service.UserStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SentenceStrategy implements StatisticStrategy {

    private final UserStatisticService userStatisticService;

    @Override
    public void resolve(StatisticEventDto statistic) {
        var userStatistic = userStatisticService.findByUserId(statistic.getUserId());
        userStatistic.setTotalCorrectSentenceAnswers(userStatistic.getTotalCorrectSentenceAnswers() + statistic.getCorrectCount());
        userStatistic.setTotalSentence(userStatistic.getTotalSentence() + statistic.getTotalItems());

        userStatisticService.save(userStatistic);
    }
}
