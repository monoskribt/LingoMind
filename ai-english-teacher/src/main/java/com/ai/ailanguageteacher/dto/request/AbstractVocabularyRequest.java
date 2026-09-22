package com.ai.ailanguageteacher.dto.request;

import com.common.dto.enums.EnglishLevel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
abstract public class AbstractVocabularyRequest {

    @NotBlank
    private String topic;

    @NotNull
    private EnglishLevel englishLevel;

    @Max(25)
    @NotNull
    private Integer amount;

}
