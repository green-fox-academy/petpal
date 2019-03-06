package com.greenfoxacademy.petpal.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateUserServiceImpl implements PrivateUserService {

  private PrivateUserRepository privateUserRepository;

  @Autowired
  public PrivateUserServiceImpl(PrivateUserRepository privateUserRepository) {
    this.privateUserRepository = privateUserRepository;
  }
}
