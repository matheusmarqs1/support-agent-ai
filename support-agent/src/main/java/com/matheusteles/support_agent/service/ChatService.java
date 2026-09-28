package com.matheusteles.support_agent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import com.matheusteles.support_agent.dto.ChatRequestDTO;
import com.matheusteles.support_agent.dto.ChatResponseDTO;
import com.matheusteles.support_agent.exception.ChatSessionNotFoundException;
import com.matheusteles.support_agent.model.ChatMessage;
import com.matheusteles.support_agent.model.ChatSession;
import com.matheusteles.support_agent.model.MessageRole;
import com.matheusteles.support_agent.repository.ChatSessionRepository;

import jakarta.transaction.Transactional;

@Service 
public class ChatService {

    private final ChatClient chatClient;
    private final ChatSessionRepository sessionRepository;

    public ChatService(ChatClient chatClient, ChatSessionRepository sessionRepository){
        this.chatClient = chatClient;
        this.sessionRepository = sessionRepository;
    }

    @Transactional 
    public ChatResponseDTO processMessage(ChatRequestDTO request){
        
        ChatSession session = request.sessionId() != null 
            ? sessionRepository.findById(request.sessionId())
                .orElseThrow(() -> new ChatSessionNotFoundException("Chat session not found with ID: " + request.sessionId()))
            : ChatSession.builder().build();
        
        List<Message> savedMessages = new ArrayList<>();
    
        for(ChatMessage message : session.getMessages()){
            if(message.getRole() == MessageRole.USER){
                savedMessages.add(new UserMessage(message.getContent()));
            }
            else if(message.getRole() == MessageRole.ASSISTANT){
                savedMessages.add(new AssistantMessage(message.getContent()));
            }
        }

        savedMessages.add(new UserMessage(request.message()));
       
        ChatMessage userMessage = ChatMessage.builder().role(MessageRole.USER)
            .content(request.message()).build();
        session.addMessage(userMessage);

        String aiResponse = chatClient.prompt().messages(savedMessages).call().content();

        ChatMessage assistantMessage = ChatMessage.builder().role(MessageRole.ASSISTANT)
            .content(aiResponse).build();
        session.addMessage(assistantMessage);

        ChatSession savedSession = sessionRepository.save(session);

        return new ChatResponseDTO(savedSession.getId(), aiResponse);
        
    }

}
