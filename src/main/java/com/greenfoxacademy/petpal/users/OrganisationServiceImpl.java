package com.greenfoxacademy.petpal.users;

import org.springframework.beans.factory.annotation.Autowired;

public class OrganisationServiceImpl implements OrganisationService {

  private OrganisationRepository organisationRepository;

  @Autowired
  public OrganisationServiceImpl(OrganisationRepository organisationRepository) {
    this.organisationRepository = organisationRepository;
  }
}
