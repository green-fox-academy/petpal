package com.greenfoxacademy.petpal.chat.controllers;

import com.greenfoxacademy.petpal.chat.models.ChatMessage;
import com.greenfoxacademy.petpal.chat.services.ChatService;
import com.greenfoxacademy.petpal.exception.ChatIdNotFoundException;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.greenfoxacademy.petpal.users.services.ParentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  private ChatService chatService;
  private ParentUserService parentUserService;

  @Autowired
  public ChatController(ChatService chatService, ParentUserService userDetailsService) {
    this.chatService = chatService;
    this.parentUserService = userDetailsService;
  }

  @GetMapping(value = "/chats")
  public ResponseEntity getChats(Authentication authentication) throws Throwable {
    ParentUser parentUser = parentUserService.getUserFromAuth(authentication);
    return ResponseEntity.ok(parentUser.getChats());
  }

  @GetMapping(value = "/chats/{chatId}")
  public ResponseEntity getChat(@PathVariable Long chatId) throws ChatIdNotFoundException {
    return ResponseEntity.ok(chatService.findById(chatId));
  }

  @PostMapping(value = "/chats/{chatId}")
  public ResponseEntity sendMessage(@PathVariable Long chatId, ChatMessage chatMessage, Authentication authentication) throws Throwable {
    ParentUser parentUser = parentUserService.getUserFromAuth(authentication);
    chatService.saveChat(chatMessage, chatId, parentUser);
    return ResponseEntity.ok(chatService.findById(chatId));
  }
}
