package com.matheusteles.support_agent.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/api/teste-ia")
    public String getResponse(@RequestParam(defaultValue = "Me diga uma curiosidade sobre Java") String message) {
        return chatClient.prompt().user(message).call().content();
    }
    
}
