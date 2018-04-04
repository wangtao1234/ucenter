package com.example.ucenter.Filter;

import lombok.Data;

/**
 * Created by admin on 2018/3/30.
 */
@Data
public class LoginPara {

    private String clientId;

    private String userName;

    private String password;

    private String captchaCode;

    private String captchaValue;
}
