package com.ai.ailanguageteacher.dto;

import com.ai.ailanguageteacher.dto.enums.PartSpeechType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    private String word;
    private String translation;
    private PartSpeechType speechType;

}
