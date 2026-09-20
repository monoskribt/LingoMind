package com.statistic.model;

import com.common.dto.enums.AttemptType;
import com.common.dto.enums.EnglishLevel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "attempt_statistic")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class AttemptStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "attempt_type")
    @Enumerated(EnumType.STRING)
    private AttemptType attemptType;

    @Column(name = "english_level")
    @Enumerated(EnumType.STRING)
    private EnglishLevel englishLevel;

    @Column(name = "correct_count")
    private Integer correctCount;

    @Column(name = "total_items")
    private Integer totalItems;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
