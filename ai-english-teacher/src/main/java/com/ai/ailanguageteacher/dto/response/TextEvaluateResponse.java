package com.ai.ailanguageteacher.dto.response;

import com.common.dto.enums.EnglishLevel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextEvaluateResponse {

    @NotBlank
    private String review;

    @NotNull
    private EnglishLevel englishLevel;

    @Min(0)
    @Max(100)
    private Integer grade;

    private String recommendation;
}
