package com.ai.ailanguageteacher.controller;

import com.ai.ailanguageteacher.dto.response.VocabularySessionFinishResponse;
import com.ai.ailanguageteacher.dto.session.QuizSession;
import com.ai.ailanguageteacher.dto.session.SentenceGapSession;
import com.ai.ailanguageteacher.service.VocabularySessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat/vocabulary/session")
@RequiredArgsConstructor
public class VocabularySessionController {

    private final VocabularySessionService service;

    @GetMapping("/start/quizlet/{userId}")
    public QuizSession getQuizSession(@PathVariable Long userId) {
        return service.getQuizSession(userId);
    }

    @GetMapping("/start/sentence-gap/{userId}")
    public SentenceGapSession getSentenceGapSession(@PathVariable Long userId) {
        return service.getSentenceGapSession(userId);
    }

    @PostMapping("/finish/quizlet/{userId}")
    public VocabularySessionFinishResponse finishQuizSession(@PathVariable Long userId) {
        return service.quizSessionFinish(userId);
    }

    @PostMapping("/finish/sentence-gap/{userId}")
    public VocabularySessionFinishResponse finishSentenceGapSession(@PathVariable Long userId) {
        return service.sentenceGapSessionFinish(userId);
    }
}
