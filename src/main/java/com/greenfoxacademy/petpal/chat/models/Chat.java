package com.greenfoxacademy.petpal.chat.models;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long unseen;

  @ManyToMany
  @JoinTable(
          name = "parent_user_chats",
          joinColumns = @JoinColumn(
                  name = "chat_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(
                  name = "parent_user_id", referencedColumnName = "id"))
  private Set<ParentUser> users;

  @OneToMany(mappedBy = "chat", cascade = CascadeType.PERSIST)
  private List<ChatMessage> messages;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinColumn(name = "animal_id")
  private Animal animal;
}
