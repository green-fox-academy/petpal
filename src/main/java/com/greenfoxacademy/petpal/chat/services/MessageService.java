package com.greenfoxacademy.petpal.chat.services;

import com.greenfoxacademy.petpal.chat.models.ChatMessage;
import com.greenfoxacademy.petpal.chat.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
  private MessageRepository messageRepository;

  @Autowired
  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  void saveMessage(ChatMessage chatMessage) {
    messageRepository.save(chatMessage);
  }
}
