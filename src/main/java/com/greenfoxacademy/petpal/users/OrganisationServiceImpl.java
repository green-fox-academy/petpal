package com.greenfoxacademy.petpal.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganisationServiceImpl implements OrganisationService {

  private OrganisationRepository organisationRepository;

  @Autowired
  public OrganisationServiceImpl(OrganisationRepository organisationRepository) {
    this.organisationRepository = organisationRepository;
  }
}