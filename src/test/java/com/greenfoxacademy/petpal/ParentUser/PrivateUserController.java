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
public class PrivateUserController {
//not only for PrivateUser

  @Autowired
  MockMvc mockMvc;

  private String name = "testuser@gmail.com";
  private String password = "password";
  private String email = "";
  private String error = "error";
  private String jsonObject = "";
  private String result = "";

  @Test
  public void registerUnsuccessfulMissingParameters() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(Constants.registerEndpoint))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void registerWithNoEmail() throws Exception {
    jsonObject = new JSONObject()
            .put("name", name)
            .put("password", password)
            .toString();

    result = new JSONObject()
            .put("status", error)
            .put("message", "Missing parameter(s): {username=must not be blank}")
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post(Constants.registerEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonObject))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(result));
  }

  @Test
  public void registerWithNoPassword() throws Exception {
    jsonObject = new JSONObject()
            .put("name", name)
            .put("email", email)
            .toString();

    result = new JSONObject()
            .put("status", error)
            .put("message", "Missing parameter(s): {password=must not be blank}")
            .toString();
    
    mockMvc.perform(MockMvcRequestBuilders.post(Constants.registerEndpoint)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonObject))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(result));

  }
}
