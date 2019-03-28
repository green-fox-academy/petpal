package com.greenfoxacademy.petpal.chat.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.chat.models.Chat;
import com.greenfoxacademy.petpal.chat.models.ChatMessage;
import com.greenfoxacademy.petpal.chat.repositories.ChatRepository;
import com.greenfoxacademy.petpal.exception.ChatIdNotFoundException;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.greenfoxacademy.petpal.users.services.ParentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ChatService {
  private ChatRepository chatRepository;
  private MessageService messageService;
  private ParentUserService parentUserService;

  @Autowired
  public ChatService(ChatRepository chatRepository, MessageService messageService, ParentUserService userDetailsService) {
    this.chatRepository = chatRepository;
    this.messageService = messageService;
    this.parentUserService = userDetailsService;
  }

  public Chat findById(Long id) throws ChatIdNotFoundException {
    return chatRepository.findById(id)
            .orElseThrow(() -> new ChatIdNotFoundException(("There is no Chat with such ID")));
  }

  public void saveChat(ChatMessage chatMessage, Long id, ParentUser parentUser) throws ChatIdNotFoundException {
    Chat chat = findById(id);
    List<ChatMessage> messages = chat.getMessages();
    messages.add(chatMessage);
    chat.setMessages(messages);
    chatMessage.setAuthor(parentUser);
    messageService.saveMessage(chatMessage);
    chatRepository.save(chat);
  }

  public void createChat(ParentUser firstUser, ParentUser secondUser, Animal animal) {
    Chat chat = new Chat();
    Set<ParentUser> users = new HashSet<>();
    users.add(firstUser);
    users.add(secondUser);
    chat.setUsers(users);
    chat.setAnimal(animal);
    Set<Chat> firstUserChat = firstUser.getChats();
    firstUserChat.add(chat);
    Set<Chat> secondUserChat = secondUser.getChats();
    secondUserChat.add(chat);
    firstUser.setChats(firstUserChat);
    secondUser.setChats(secondUserChat);
    chatRepository.save(chat);
    parentUserService.saveUser(firstUser);
    parentUserService.saveUser(secondUser);
  }
}
