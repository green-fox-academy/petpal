package com.greenfoxacademy.petpal.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganisationServiceImpl implements OrganisationService {

  private MainUserRepository mainUserRepository;

  @Autowired
  public OrganisationServiceImpl(MainUserRepository mainUserRepository) {
    this.mainUserRepository = mainUserRepository;
  }
}