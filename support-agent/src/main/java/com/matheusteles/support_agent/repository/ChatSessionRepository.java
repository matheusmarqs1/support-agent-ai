package com.matheusteles.support_agent.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusteles.support_agent.model.ChatSession;

public interface ChatSessionRepository extends JpaRepository<ChatSession, UUID> {
}
