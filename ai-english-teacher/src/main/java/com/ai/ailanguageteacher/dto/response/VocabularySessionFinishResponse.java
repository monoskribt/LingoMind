package com.ai.ailanguageteacher.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VocabularySessionFinishResponse {

    private int correct;
    private int totalAmount;

}
