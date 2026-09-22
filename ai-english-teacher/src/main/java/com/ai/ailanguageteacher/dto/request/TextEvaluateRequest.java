package com.ai.ailanguageteacher.dto.request;

import com.common.dto.enums.EnglishLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextEvaluateRequest {

    @NotBlank
    private String content;

    @NotNull
    private EnglishLevel englishLevel;

}
