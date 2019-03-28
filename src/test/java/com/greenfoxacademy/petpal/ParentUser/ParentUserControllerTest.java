package com.greenfoxacademy.petpal.ParentUser;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ParentUserControllerTest {

  @Autowired
  MockMvc mockMvc;

  private String name = "testUser";
  private String password = "password";
  private String email = "testuser@gmail.com";
  private String error = "error";
  private String user;
  private String response = "";

 /* @Test
  public void register_missingParameters_returnsBadRequest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(Constants.registerEndpoint))
            .andExpect(status().isBadRequest());
  }*/

  @Test
  public void register_missingEmail_returnsBadRequest() throws Exception {
    user = new JSONObject()
            .put("name", name)
            .put("password", password)
            .toString();

    response = new JSONObject()
            .put("status", error)
            .put("message", "Missing parameter(s): {email=must not be blank}")
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post(Constants.registerEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(user))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(response));
  }

  @Test
  public void register_missingPassword_returnsBadRequest() throws Exception {
    user = new JSONObject()
            .put("name", name)
            .put("email", email)
            .toString();

    response = new JSONObject()
            .put("status", error)
            .put("message", "Missing parameter(s): {password=must not be blank}")
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post(Constants.registerEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(user))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(response));
  }

  @Test
  public void login_missingEmail_returnsBadRequest() throws Exception {
    user = new JSONObject()
            .put("password", password)
            .toString();

    response = new JSONObject()
            .put("status", error)
            .put("message", "Missing parameter(s): {email=must not be blank}")
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post(Constants.loginEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(user))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(response));
  }

  @Test
  public void login_missingPassword_returnsBadRequest() throws Exception {
    user = new JSONObject()
            .put("email", email)
            .toString();

    response = new JSONObject()
            .put("status", error)
            .put("message", "Missing parameter(s): {password=must not be blank}")
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post(Constants.loginEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(user))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(response));
  }
}