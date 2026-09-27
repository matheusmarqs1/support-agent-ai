package com.matheusteles.support_agent.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusteles.support_agent.model.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {
}
