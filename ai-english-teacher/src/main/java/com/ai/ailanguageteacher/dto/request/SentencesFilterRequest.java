package com.ai.ailanguageteacher.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SentencesFilterRequest extends AbstractVocabularyRequest {

    @Min(1)
    @Max(3)
    private Integer gapsPerSentence;

    private boolean useDistractors = true;

}
