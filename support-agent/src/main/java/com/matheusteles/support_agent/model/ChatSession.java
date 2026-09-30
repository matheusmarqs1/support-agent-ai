package com.matheusteles.support_agent.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "tb_chat_session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class ChatSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100)
    private String title;

    @Column (nullable = false, updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "chatSession", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt ASC")
    @Builder.Default
    private List<ChatMessage> messages = new ArrayList<>();

    @PrePersist 
    public void prePersist(){
        this.createdAt = Instant.now();
    }

    public void addMessage(ChatMessage message) {
        messages.add(message);
        message.setChatSession(this);
    }
}
