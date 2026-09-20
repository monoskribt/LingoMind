package com.statistic.interfaces;

import com.common.dto.StatisticEventDto;

public interface StatisticStrategy {

    void resolve(StatisticEventDto statistic);

}
