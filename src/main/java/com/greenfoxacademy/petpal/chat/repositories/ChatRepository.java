package com.greenfoxacademy.petpal.chat.repositories;

import com.greenfoxacademy.petpal.chat.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
