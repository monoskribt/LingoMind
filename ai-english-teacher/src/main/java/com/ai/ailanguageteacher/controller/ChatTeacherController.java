package com.ai.ailanguageteacher.controller;

import com.ai.ailanguageteacher.dto.ChatDto;
import com.ai.ailanguageteacher.dto.request.CreateChatRequest;
import com.ai.ailanguageteacher.dto.request.SendMessageRequest;
import com.ai.ailanguageteacher.service.ChatTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/chat/teacher")
@RequiredArgsConstructor
public class ChatTeacherController {

    private final ChatTeacherService service;

    @PostMapping("/create/{userId}")
    public void create(@RequestBody @Valid CreateChatRequest request, @PathVariable Long userId) {
        service.createChat(request, userId);
    }

    @GetMapping("/all-chats/{userId}")
    public List<ChatDto> getAll(@PathVariable Long userId) {
        return service.getAll(userId);
    }

    @PostMapping(value = "{id}/message/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> communicate(@RequestBody @Valid SendMessageRequest request,
                                    @PathVariable Long id, @PathVariable Long userId) {
        return service.communicate(request.getContent(), id, userId);
    }
}
