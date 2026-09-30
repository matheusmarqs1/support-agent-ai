package com.matheusteles.support_agent.dto;

import java.time.Instant;
import java.util.UUID;

import com.matheusteles.support_agent.model.MessageRole;

public record ChatMessageResponseDTO(
    UUID id,
    MessageRole role,
    String content,
    Instant createdAt
) {

}
