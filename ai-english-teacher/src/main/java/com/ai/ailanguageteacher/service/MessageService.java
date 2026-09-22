package com.ai.ailanguageteacher.service;

import com.ai.ailanguageteacher.model.Chat;
import com.ai.ailanguageteacher.model.ChatMessage;
import com.ai.ailanguageteacher.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    public void saveUserMessage(String content, Chat chat, Long userId) {
        var message = new ChatMessage();
        message.setUserId(userId);
        message.setMessageType(MessageType.USER);
        message.setContent(content);
        message.setChat(chat);

        try {
            var saved = repository.save(message);
            log.debug("User Message is successfully saved with id: {} and with chatId: {}",
                    saved.getId(), chat.getId());
        } catch (Exception e) {
            log.error("Failed to save user message with chatId: {}", chat.getId(), e);
        }
    }

    public void saveAIMessage(String answer, Chat chat) {
        var message = new ChatMessage();
        message.setUserId(null);
        message.setMessageType(MessageType.ASSISTANT);
        message.setContent(answer);
        message.setChat(chat);

        try {
            var saved = repository.save(message);
            log.debug("AI Message is successfully saved with id: {} and with chatId: {}",
                    saved.getId(), chat.getId());
        } catch (Exception e) {
            log.error("Failed to save AI message with chatId: {}", chat.getId(), e);
        }
    }
}
