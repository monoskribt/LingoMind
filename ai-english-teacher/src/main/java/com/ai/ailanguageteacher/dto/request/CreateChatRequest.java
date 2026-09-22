package com.ai.ailanguageteacher.dto.request;

import com.ai.ailanguageteacher.dto.enums.ChatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatRequest {

    @NotNull
    private ChatType type;

}
