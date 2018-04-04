package com.example.ucenter.mapper;

import com.example.ucenter.entity.UserInfo;

/**
 * Created by admin on 2018/3/30.
 */
public interface UserRepository {
    UserInfo findByUserName(String userName);
}
