package com.example.ucenter.security;

import lombok.Data;

/**
 * Created by admin on 2018/3/30.
 */
@Data
public class AccessToken {

    private  String access_token;

    private String token_type;

    private  long expires_in;

    private String userId;
}
