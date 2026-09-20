package com.statistic.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_attempt_summary")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UserAttemptSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total_correct_quiz_answers")
    private Integer totalCorrectQuizAnswers;

    @Column(name = "total_correct_sentence_answers")
    private Integer totalCorrectSentenceAnswers;

    @Column(name = "total_quiz_attempts")
    private Integer totalQuiz;

    @Column(name = "total_sentence_attempts")
    private Integer totalSentence;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
