package com.ai.ailanguageteacher.dto.session;

import com.ai.ailanguageteacher.dto.SentenceGapCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SentenceGapSession extends CacheDto {

    private List<SentenceGapCard> cards;

}
