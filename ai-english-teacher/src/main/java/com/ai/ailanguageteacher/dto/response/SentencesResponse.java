package com.ai.ailanguageteacher.dto.response;

import com.ai.ailanguageteacher.dto.SentenceGapCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SentencesResponse extends AbstractVocabularyResponse {

    @Valid
    private List<SentenceGapCard> cards;

}
