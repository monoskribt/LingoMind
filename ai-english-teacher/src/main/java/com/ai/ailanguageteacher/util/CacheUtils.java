package com.ai.ailanguageteacher.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CacheUtils {

    public static final String QUIZ_CACHE = "quizCache";
    public static final String SENTENCE_GAP_CACHE = "sentenceGapCache";

    public static final String QUIZ_CACHE_KEY = "quiz:%d";
    public static final String SENTENCE_GAP_CACHE_KEY = "quiz:%d";

}
