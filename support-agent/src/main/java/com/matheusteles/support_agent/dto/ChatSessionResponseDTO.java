package com.matheusteles.support_agent.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ChatSessionResponseDTO(
    UUID id,
    String title,
    Instant createdAt,
    List<ChatMessageResponseDTO> messages
) {

}
