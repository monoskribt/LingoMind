package com.ai.ailanguageteacher.dto;

import com.ai.ailanguageteacher.model.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {

    private Long id;
    private List<ChatMessageDto> chatMessages;

}
