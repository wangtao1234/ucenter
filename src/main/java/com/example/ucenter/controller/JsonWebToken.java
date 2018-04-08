package com.example.ucenter.controller;

import com.example.ucenter.Filter.LoginPara;
import com.example.ucenter.mapper.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2018/3/30.
 */
@RestController
public class JsonWebToken {

    @Autowired
    private UserRepository userRepository;
    @RequestMapping("wtoauth/wttoken")
    public Object getAccessToken( LoginPara loginPara){
        String  resultMsg ="";
        return resultMsg;
    }
}
