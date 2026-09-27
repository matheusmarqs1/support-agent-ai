package com.matheusteles.support_agent.dto;

import java.util.UUID;

public record ChatRequestDTO(
    UUID sessionId,
    String message
) {

}
