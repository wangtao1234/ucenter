package com.example.ucenter.service.impl;

import com.example.ucenter.Filter.LoginPara;

/**
 * Created by admin on 2018/4/8.
 */
public interface UserAuth {

    Object aouthToken(LoginPara loginPara);
}
