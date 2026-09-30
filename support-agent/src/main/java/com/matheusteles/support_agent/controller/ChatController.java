package com.matheusteles.support_agent.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.matheusteles.support_agent.dto.ChatRequestDTO;
import com.matheusteles.support_agent.dto.ChatResponseDTO;
import com.matheusteles.support_agent.dto.ChatSessionResponseDTO;
import com.matheusteles.support_agent.dto.ChatSessionSummaryDTO;
import com.matheusteles.support_agent.service.ChatService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/sessions") 
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<ChatResponseDTO> askAgent(@Valid @RequestBody ChatRequestDTO request) {
        ChatResponseDTO response = chatService.processMessage(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ChatSessionSummaryDTO>> getSessions() {
        List<ChatSessionSummaryDTO> sessions = chatService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<ChatSessionResponseDTO> getSession(@PathVariable UUID sessionId){
        ChatSessionResponseDTO session = chatService.getSessionById(sessionId);
        return ResponseEntity.ok(session);
    }
    
    
}
