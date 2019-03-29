package com.greenfoxacademy.petpal.ParentUser;

import com.greenfoxacademy.petpal.exception.EmailTakenException;
import com.greenfoxacademy.petpal.oauthSecurity.Token;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import com.greenfoxacademy.petpal.users.models.dtos.LoginUserDTO;
import com.greenfoxacademy.petpal.users.models.dtos.RegisterUserDTO;
import com.greenfoxacademy.petpal.users.services.ParentUserService;
import com.mashape.unirest.http.exceptions.UnirestException;
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
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ParentUserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ParentUserService userDetailsService;
  private String name = "testUser";
  private String password = "password";
  private String email = "testuser@gmail.com";
  private String error = "error";
  private String user;
  private String tokenString;
  private Token token;
  private String missingEmailParam;
  private String missingPasswordParam;
  private RegisterUserDTO testuser;
  private PrivateUser privateUser;
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
    modelMapper = new ModelMapper();
    privateUser = modelMapper.map(testuser, PrivateUser.class);

  }

  @Test
  public void register_successful() throws Exception {
    user = new JSONObject()
            .put("name", name)
            .put("email", email)
            .put("password", password)
            .toString();
    when(userDetailsService.register(any(PrivateUser.class))).thenReturn(privateUser);
    mockMvc.perform(MockMvcRequestBuilders.post(Constants.registerEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(user))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
            .andExpect(content().string(email));
  }

  @Test
  public void register_missingParameters_returnsBadRequest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(Constants.registerEndpoint))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void register_missingEmail_returnsBadRequest() throws Exception {
    user = new JSONObject()
            .put("name", name)
            .put("password", password)
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post(Constants.registerEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(user))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(missingEmailParam));

  }

  @Test
  public void register_missingPassword_returnsBadRequest() throws Exception {
    user = new JSONObject()
            .put("name", name)
            .put("email", email)
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post(Constants.registerEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(user))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(missingPasswordParam));
  }

/*  @Test
 public void login_successful() throws Exception {
    user = new JSONObject()
            .put("email", email)
            .put("password", password)
            .toString();
    when(userDetailsService.login(any(PrivateUser.class))).thenReturn(tokenString);
    mockMvc.perform(MockMvcRequestBuilders.post(Constants.loginEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(user))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.token").exists())
            .andExpect(jsonPath("$.token").isNotEmpty())
            .andExpect(jsonPath("$.token").isString());
  }*/

  @Test
  public void login_missingEmail_returnsBadRequest() throws Exception {
    user = new JSONObject()
            .put("password", password)
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post(Constants.loginEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(user))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(missingEmailParam));
  }

  @Test
  public void login_missingPassword_returnsBadRequest() throws Exception {
    user = new JSONObject()
            .put("email", email)
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post(Constants.loginEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(user))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(missingPasswordParam));
  }

}
