package com.greenfoxacademy.petpal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class PetpalApplication extends WebSecurityConfigurerAdapter {

  @RequestMapping("/user")
  public Principal user(Principal principal) {
    return principal;
  }

/*  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**", "/error**").permitAll().anyRequest()
            .authenticated();
  }*/

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/", "/login**", "/webjars/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and().logout().logoutSuccessUrl("/").permitAll();
  }
/*
  @RequestMapping("/user")
  public Principal user(Principal principal) {
    return principal;
  }
  */

  public static void main(String[] args) {
    SpringApplication.run(PetpalApplication.class, args);
  }

}
