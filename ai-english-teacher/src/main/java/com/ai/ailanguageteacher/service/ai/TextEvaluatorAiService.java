package com.ai.ailanguageteacher.service.ai;

import com.ai.ailanguageteacher.dto.request.TextEvaluateRequest;
import com.ai.ailanguageteacher.dto.response.TextEvaluateResponse;
import com.ai.ailanguageteacher.mapper.ContentMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@Service
public class TextEvaluatorAiService {

    private final ChatClient chatClient;
    private final ContentMapper mapper;

    @Value("classpath:/prompt-templates/english-text-evaluator.st")
    Resource textEvaluatorPrompt;

    public TextEvaluatorAiService(@Qualifier("chatClientTextEvaluator") ChatClient chatClient,
                                  ContentMapper mapper) {
        this.chatClient = chatClient;
        this.mapper = mapper;
    }

    public TextEvaluateResponse evaluate(Long userId, TextEvaluateRequest request) {
        return chatClient.prompt()
                .user(mapper.toJson(request))
                .system(textEvaluatorPrompt)
                .advisors(a -> a.param(CONVERSATION_ID, userId))
                .call()
                .responseEntity(TextEvaluateResponse.class)
                .entity();
    }
}
