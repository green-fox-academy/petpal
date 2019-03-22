package com.greenfoxacademy.petpal.oauthSecurity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GoogleOAuth2UserInfo {

    private String id;
    private String name;
    private String email;
    private String imageUrl;
}
