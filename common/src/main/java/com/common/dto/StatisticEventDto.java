package com.common.dto;

import com.common.dto.enums.AttemptType;
import com.common.dto.enums.EnglishLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticEventDto {

    private Long userId;
    private AttemptType attemptType;
    private EnglishLevel englishLevel;
    private Integer correctCount;
    private Integer totalItems;

}
