package com.ai.ailanguageteacher.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressSessionDto {

    private int currentIndex;
    private boolean correct;
}
