package com.greenfoxacademy.petpal.oauthSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {
    @GetMapping("/login")
    public String loadLoginPage(){
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }



}