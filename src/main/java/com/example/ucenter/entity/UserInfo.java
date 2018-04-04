package com.example.ucenter.entity;

import lombok.Data;

/**
 * Created by admin on 2018/4/2.
 */
@Data
public class UserInfo {

    private int id;

    private String name;

    private  String password;

    private String salt;

    private  String role;

}
