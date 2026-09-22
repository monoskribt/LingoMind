package com.ai.ailanguageteacher.dto.session;

import com.ai.ailanguageteacher.dto.Card;
import com.ai.ailanguageteacher.dto.enums.QuizletType;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class QuizSession extends CacheDto {

    private QuizletType quizletType;
    private List<Card> cards;

}
