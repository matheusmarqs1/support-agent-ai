package com.matheusteles.support_agent.exception;

public class ChatSessionNotFoundException extends RuntimeException {
    public ChatSessionNotFoundException(String message){
        super(message);
    }
}
