package com.greenfoxacademy.petpal.Animal;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.models.AnimalDTO;
import com.greenfoxacademy.petpal.oauthSecurity.Token;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import com.greenfoxacademy.petpal.users.models.dtos.RegisterUserDTO;
import com.greenfoxacademy.petpal.users.services.ParentUserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AnimalControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  ParentUserService userDetailsService;
  private String name = "testUser";
  private String password = "password";
  private String email = "testuser@gmail.com";
  private String error = "error";
  private String animalName = "testAnimalName";
  private String animalType = "dog";
  private String user;
  private String tokenString;
  private Token token;
  private String missingEmailParam;
  private String missingPasswordParam;
  private RegisterUserDTO testuser;
  private PrivateUser privateUser;
  private AnimalDTO testAnimal;
  private Animal animal;
  private Set<Animal> testAnimalSet;
  private ModelMapper modelMapper;


  @Before
  public void init() throws JSONException {
    tokenString = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b3Rob3ZhZW5pa285MEBnbWFpbC5jb20iLCJzY29wZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlzcyI6InBldHBhbCIsImlhdCI6MTU1MzgwMTc5NywiZXhwIjoxNTUzODE5Nzk3fQ.5TfKG6poFvCgBIA-a1Un0NqIGhTUzNyTjdCAIR9ECdQ";
    token = new Token(tokenString);
    missingEmailParam = new JSONObject()
            .put("status", error)
            .put("message", "Missing parameter(s): {email=must not be blank}")
            .toString();
    missingPasswordParam =new JSONObject()
            .put("status", error)
            .put("message", "Missing parameter(s): {password=must not be blank}")
            .toString();
    testuser = RegisterUserDTO.builder()
            .email(email)
            .name(name)
            .password(password)
            .build();
    testAnimal = AnimalDTO.builder()
            .name(animalName)
            .type(animalType)
            .build();
    modelMapper = new ModelMapper();
    privateUser = modelMapper.map(testuser, PrivateUser.class);
    animal = modelMapper.map(testAnimal, Animal.class);
    testAnimalSet = new HashSet<>();
    testAnimalSet.add(animal);

  }

  @Test
  public void pets_successful() {
    when(userDetailsService.findAllAdoptableAnimals(any(PrivateUser.class))).thenReturn(testAnimalSet);
  }
}
