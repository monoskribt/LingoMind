package com.statistic.repository;

import com.statistic.model.UserAttemptSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatisticRepository extends JpaRepository<UserAttemptSummary, Long> {

    Optional<UserAttemptSummary> findByUserId(Long userId);

}
