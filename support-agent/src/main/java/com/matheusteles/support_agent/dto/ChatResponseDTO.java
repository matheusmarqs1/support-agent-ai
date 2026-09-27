package com.matheusteles.support_agent.dto;

import java.util.UUID;

public record ChatResponseDTO(
    UUID sessionID,
    String response
) {

}
