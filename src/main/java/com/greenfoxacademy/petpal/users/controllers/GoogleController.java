
package com.greenfoxacademy.petpal.users.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoogleController {

  @GetMapping("/googleauth")
  public String index(){
    return "index";
  }

}

