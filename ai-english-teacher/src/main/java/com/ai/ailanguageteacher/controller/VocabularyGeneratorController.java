package com.ai.ailanguageteacher.controller;

import com.ai.ailanguageteacher.dto.request.QuizletFilterRequest;
import com.ai.ailanguageteacher.dto.request.SentencesFilterRequest;
import com.ai.ailanguageteacher.dto.response.QuizletResponse;
import com.ai.ailanguageteacher.dto.response.SentencesResponse;
import com.ai.ailanguageteacher.service.ai.VocabularyAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat/vocabulary/generate")
@RequiredArgsConstructor
public class VocabularyGeneratorController {

    private final VocabularyAiService vocabularyService;

    @PostMapping("/quizlet/{userId}")
    public QuizletResponse generateQuizlet(@RequestBody @Valid QuizletFilterRequest request,
                                           @PathVariable Long userId) {
        return vocabularyService.generateQuizlet(request, userId);
    }

    @PostMapping("/sentence-gap/{userId}")
    public SentencesResponse generateSentencesWithGap(@RequestBody @Valid SentencesFilterRequest request,
                                                      @PathVariable Long userId) {
        return vocabularyService.generateSentencesWithGap(request, userId);
    }
}
