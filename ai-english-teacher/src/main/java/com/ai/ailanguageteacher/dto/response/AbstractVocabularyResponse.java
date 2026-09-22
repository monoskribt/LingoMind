package com.ai.ailanguageteacher.dto.response;

import com.common.dto.enums.EnglishLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractVocabularyResponse {

    @NotBlank
    private String topic;

    @NotBlank
    private EnglishLevel englishLevel;

    @NotNull
    private Integer amount;

}
