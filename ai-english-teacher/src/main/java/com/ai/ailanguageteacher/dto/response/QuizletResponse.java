package com.ai.ailanguageteacher.dto.response;

import com.ai.ailanguageteacher.dto.Card;
import com.ai.ailanguageteacher.dto.enums.QuizletType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QuizletResponse extends AbstractVocabularyResponse {

    @NotNull
    private QuizletType quizletType;

    @NotEmpty
    private List<Card> cards;

}
