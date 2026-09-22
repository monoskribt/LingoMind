package com.ai.ailanguageteacher.dto.session;

import com.common.dto.enums.EnglishLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheDto {

    private int currentIndex;
    private int correct;
    private int incorrect;
    private EnglishLevel level;

}
