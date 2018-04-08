package com.example.ucenter.Filter;

import lombok.Data;

/**
 * Created by admin on 2018/3/30.
 * 登录获取token时，所需要的认证信息类
 */
@Data
public class LoginPara {
    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captchaCode;

    /**
     * 验证值
     */
    private String captchaValue;
}
