package com.matheusteles.support_agent.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.matheusteles.support_agent.dto.ChatRequestDTO;
import com.matheusteles.support_agent.dto.ChatResponseDTO;

@Service 
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    public ChatResponseDTO processMessage(ChatRequestDTO request){
        String response = chatClient.prompt().user(request.message()).call().content();
        return new ChatResponseDTO(response);
    }

}
