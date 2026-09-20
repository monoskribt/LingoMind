package com.statistic.service;

import com.statistic.dto.AttemptStatisticDto;
import com.statistic.mapper.AttemptStatisticMapper;
import com.statistic.model.AttemptStatistic;
import com.statistic.repository.AttemptStatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttemptStatisticService {

    private final AttemptStatisticRepository repository;
    private final AttemptStatisticMapper mapper;

    public Page<AttemptStatisticDto> findByUserId(Long userId, Pageable pageable) {
        return mapper.mapDtos(repository.findAllByUserIdOrderByCreatedAtDesc(userId, pageable));
    }

    public void save(AttemptStatistic stats) {
        repository.save(stats);
    }

}
