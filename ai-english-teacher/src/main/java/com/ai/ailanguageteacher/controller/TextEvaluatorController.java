package com.ai.ailanguageteacher.controller;

import com.ai.ailanguageteacher.dto.request.TextEvaluateRequest;
import com.ai.ailanguageteacher.dto.response.TextEvaluateResponse;
import com.ai.ailanguageteacher.service.ai.TextEvaluatorAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat/text-evaluator")
@RequiredArgsConstructor
public class TextEvaluatorController {

    private final TextEvaluatorAiService service;

    @PostMapping("/{userId}/evaluate")
    public TextEvaluateResponse evaluate(@PathVariable Long userId,
                                         @RequestBody @Valid TextEvaluateRequest request) {
        return service.evaluate(userId, request);
    }
}
