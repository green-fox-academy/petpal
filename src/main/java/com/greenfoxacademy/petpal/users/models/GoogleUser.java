package com.greenfoxacademy.petpal.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfoxacademy.petpal.animal.models.Animal;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

public class GoogleUser extends ParentUser {

    @ManyToMany(mappedBy = "privateUser", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Animal> animalsLikedByUser;

    @OneToMany(mappedBy = "privateUser", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Animal> animalsToAdoptByUser;

}
