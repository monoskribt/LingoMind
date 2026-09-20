package com.statistic.mapper;

import com.common.dto.StatisticEventDto;
import com.statistic.dto.AttemptStatisticDto;
import com.statistic.model.AttemptStatistic;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface AttemptStatisticMapper {

    AttemptStatistic mapEntity(StatisticEventDto statistic);

    AttemptStatisticDto mapDto(AttemptStatistic entity);

    Page<AttemptStatisticDto> mapDtos(Page<AttemptStatistic> entities);

}
