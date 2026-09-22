package com.ai.ailanguageteacher.dto.request;

import com.ai.ailanguageteacher.dto.enums.MotherTongue;
import com.ai.ailanguageteacher.dto.enums.PartSpeechType;
import com.ai.ailanguageteacher.dto.enums.QuizletType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QuizletFilterRequest extends AbstractVocabularyRequest {

    @NotNull
    private MotherTongue motherTongue;

    @NotNull
    private QuizletType quizletType;

    @NotEmpty
    private List<PartSpeechType> partSpeech;

}
