package com.ai.ailanguageteacher.service.ai;

import com.ai.ailanguageteacher.model.Chat;
import com.ai.ailanguageteacher.service.MessageService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@Service
public class ChatTeacherAiService {

    private final ChatClient chatClient;
    private final MessageService messageService;

    @Value("classpath:/prompt-templates/english-teacher-system.st")
    Resource englishTeacherPrompt;

    public ChatTeacherAiService(@Qualifier("chatClientEnglishTeacher") ChatClient chatClient,
                                MessageService messageService) {
        this.chatClient = chatClient;
        this.messageService = messageService;
    }

    public Flux<String> communicate(Chat chat, String content, Long userId) {
        var fullAIAnswer = new StringBuilder();

        messageService.saveUserMessage(content, chat, userId);

        return chatClient.prompt()
                .user(content)
                .system(englishTeacherPrompt)
                .advisors(a -> a.param(CONVERSATION_ID, userId))
                .stream()
                .content()
                .doOnNext(fullAIAnswer::append)
                .doOnComplete(() -> messageService.saveAIMessage(fullAIAnswer.toString(), chat));
    }
}
