package com.greenfoxacademy.petpal.chat.repositories;

import com.greenfoxacademy.petpal.chat.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
}
