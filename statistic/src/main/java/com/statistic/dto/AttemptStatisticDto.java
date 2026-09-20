package com.statistic.dto;

import com.common.dto.enums.AttemptType;
import com.common.dto.enums.EnglishLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttemptStatisticDto {

    private Long id;
    private AttemptType attemptType;
    private EnglishLevel englishLevel;
    private Integer correctCount;
    private Integer totalItems;

}
