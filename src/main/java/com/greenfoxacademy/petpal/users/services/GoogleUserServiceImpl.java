package com.greenfoxacademy.petpal.users.services;


import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.exception.EmailTakenException;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

public class GoogleUserServiceImpl extends ParentUserService {

    @Override
    public ParentUser login(ParentUser parentUser) {
        return null;
    }

    @Override
    public ParentUser register(ParentUser parentUser) throws EmailTakenException, UnirestException {
        return null;
    }

    @Override
    public Set<Animal> animalsLikedByUser(ParentUser parentUser) {
        return null;
    }

    @Override
    public Set<Animal> animalsToAdoptByUser(ParentUser parentUser) {
        return null;
    }

    @Override
    public void addAnimalToAnimalsLikedByUser(Animal animal, ParentUser parentUser) {

    }

    @Override
    public void addAnimalToAnimalsToAdoptByUser(Animal animal, ParentUser parentUser) {

    }

    @Override
    public void addAnimalToAnimalsOwnedByUser(Animal animal, ParentUser parentUser) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
