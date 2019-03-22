package com.greenfoxacademy.petpal.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfoxacademy.petpal.animal.models.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "GoogleUser")
@DiscriminatorValue("GoogleUser")
@Getter
@Setter
@AllArgsConstructor
public class GoogleUser extends ParentUser {

/*    @ManyToMany(mappedBy = "parentUser", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Animal> animalsLikedByUser;

    @OneToMany(mappedBy = "parentUser", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Animal> animalsToAdoptByUser;*/

}
