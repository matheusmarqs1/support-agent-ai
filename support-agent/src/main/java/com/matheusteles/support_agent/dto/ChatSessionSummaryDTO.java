package com.matheusteles.support_agent.dto;

import java.time.Instant;
import java.util.UUID;

public record ChatSessionSummaryDTO(
    UUID id,
    String title,
    Instant createdAT
) {

}
