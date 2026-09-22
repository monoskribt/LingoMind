package com.ai.ailanguageteacher.service;

import com.ai.ailanguageteacher.dto.ChatDto;
import com.ai.ailanguageteacher.dto.ChatMessageDto;
import com.ai.ailanguageteacher.dto.request.CreateChatRequest;
import com.ai.ailanguageteacher.exception.CreateEntityException;
import com.ai.ailanguageteacher.exception.NotFoundException;
import com.ai.ailanguageteacher.model.Chat;
import com.ai.ailanguageteacher.repository.ChatRepository;
import com.ai.ailanguageteacher.service.ai.ChatTeacherAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatTeacherService {

    private final ChatTeacherAiService service;
    private final ChatRepository repository;
    private final UserService userService;

    public void createChat(CreateChatRequest request, Long userId) {
        var user = userService.findById(userId);

        var chat = new Chat();
        chat.setChatUser(user);
        chat.setType(request.getType());

        try {
            var saved = repository.save(chat);
            log.debug("Chat is successfully saved with type: {} and id: {}", request.getType(), saved.getId());
        } catch (Exception e) {
            throw new CreateEntityException("Chat is not created with type: %s".formatted(request.getType()));
        }
    }

    public List<ChatDto> getAll(Long userId) {
        var chats = repository.findAllByChatUserId(userId);

        if (chats.isEmpty()) {
            throw new NotFoundException("Chats for userId: %d is not present yet".formatted(userId));
        }

        return chats.stream()
                .map(chat -> new ChatDto(chat.getId(), chat.getChatMessages().stream()
                        .map(message -> new ChatMessageDto(message.getId(),
                                message.getMessageType(),
                                message.getContent()))
                        .toList()))
                .toList();
    }

    public Flux<String> communicate(String content, Long chatId, Long userId) {
        var chat = getChat(chatId);
        return service.communicate(chat, content, userId);
    }

    private Chat getChat(Long chatId) {
        return repository.findById(chatId)
                .orElseThrow(() -> new NoSuchElementException("Chat is not present with id: %d".formatted(chatId)));
    }
}
