package com.statistic.service;

import com.common.dto.StatisticEventDto;
import com.statistic.mapper.AttemptStatisticMapper;
import com.statistic.util.StatisticStrategyResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticStrategyResolver statisticStrategyResolver;
    private final AttemptStatisticService attemptStatisticService;
    private final AttemptStatisticMapper attemptStatisticMapper;

    @Transactional
    public void processUserStatistic(StatisticEventDto statistic) {
        attemptStatisticService.save(attemptStatisticMapper.mapEntity(statistic));
        statisticStrategyResolver.resolveStrategy(statistic.getAttemptType()).resolve(statistic);
    }
}
