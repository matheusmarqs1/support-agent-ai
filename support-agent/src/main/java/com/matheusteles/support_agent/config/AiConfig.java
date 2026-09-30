package com.matheusteles.support_agent.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class AiConfig {

    @Bean 
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("""
                        You are a Senior Software Support Engineer. 
                        Your role is to analyze logs, database errors, and application bugs. 
                        Provide direct technical diagnoses and suggest practical commands 
                        or solutions. Never act like a generic virtual assistant.
                        """)
                .build();
    }

    @Bean
    public ChatClient titleChatClient(ChatClient.Builder builder){
        return builder
                .defaultSystem("""
                        Generate a short title for a support conversation.

                        Rules:
                        - Return only the title.
                        - Use between 3 and 8 words.
                        - Do not use quotation marks.
                        - Do not add explanations, punctuation, or formatting.
                        - Preserve the language of the user's message.
                        """)
                .build();
    }

}
