package com.statistic.repository;

import com.statistic.model.AttemptStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttemptStatisticRepository extends JpaRepository<AttemptStatistic, Long> {

    Page<AttemptStatistic> findAllByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

}
