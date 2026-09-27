package com.matheusteles.support_agent.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String askAgent(@RequestParam(defaultValue = "Hello, who are you?") String prompt) {
        return chatClient.prompt().user(prompt).call().content();
    }
    
}
