package com.statistic.service;

import com.statistic.model.UserAttemptSummary;
import com.statistic.repository.UserStatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStatisticService {

    private final UserStatisticRepository repository;

    public UserAttemptSummary findByUserId(Long userId) {
        return repository.findByUserId(userId).orElse(buildDefaultUserStatistic(userId));
    }

    public void save(UserAttemptSummary stats) {
        repository.save(stats);
    }

    private UserAttemptSummary buildDefaultUserStatistic(Long userId) {
        return UserAttemptSummary.builder()
                .userId(userId)
                .totalCorrectQuizAnswers(0)
                .totalCorrectSentenceAnswers(0)
                .totalQuiz(0)
                .totalSentence(0)
                .build();
    }

}
