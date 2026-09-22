package com.ai.ailanguageteacher.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SentenceGapCard {

    @NotBlank
    private String sentenceWithGap;

    @NotEmpty
    private List<String> options;

    @NotBlank
    private String correctAnswer;

}
