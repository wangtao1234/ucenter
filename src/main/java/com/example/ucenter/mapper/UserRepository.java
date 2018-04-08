package com.example.ucenter.mapper;

import com.example.ucenter.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by admin on 2018/3/30.
 */
public interface UserRepository {
    UserInfo findByUserName(@Param("name") String userName);
}
