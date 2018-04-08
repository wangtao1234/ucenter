package com.example.ucenter.service.impl;

import com.example.ucenter.Filter.LoginPara;
import com.example.ucenter.security.AccessToken;
import com.example.ucenter.entity.UserInfo;
import com.example.ucenter.mapper.UserRepository;
import com.example.ucenter.utils.JwtHelper;
import com.example.ucenter.utils.ResultMsg;
import com.example.ucenter.utils.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2018/4/8.
 */
@Service
public class UserAuthImpl implements UserAuth {
    @Value("${audience.clientId}")
    private String clientId;

    @Value("${audience.base64Secret}")
    private String base64Secret;

    @Value("${audience.name}")
    private String name;

    @Value("${audience.expiresSecond}")
    private String expiresSecond;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Object aouthToken(LoginPara loginPara) {
        ResultMsg resultMsg;
        if(loginPara.getClientId() == null
                || (loginPara.getClientId().compareTo(clientId)!=0)){
            resultMsg = new ResultMsg(ResultStatusCode.INVALID_CLIENTID.getCode(),
                    ResultStatusCode.INVALID_CLIENTID.getMsg(),null);
            return  resultMsg;
        }
        //验证用户名和密码
        UserInfo user = userRepository.findByUserName(loginPara.getUserName());
        if(user == null){
            resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getCode(),
                    ResultStatusCode.INVALID_PASSWORD.getMsg(),null);
            return resultMsg;
        }else{
//            String md5Password = MyUtils.getMD5(loginPara.getPassword() + user.getSalt());
//            if(md5Password.compareTo(user.getPassword()) != 0 ){
//                resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getCode(),
//                        ResultStatusCode.INVALID_PASSWORD.getMsg(),null);
//                return resultMsg;
//            }
        }

        //拼装accessToken
        String accessToken  = JwtHelper.createJWT(loginPara.getUserName(),
                String.valueOf(user.getName()),user.getRole(),clientId,name,
                Long.parseLong(expiresSecond) * 1000,base64Secret);
//        //返回accessToken
        AccessToken accessTokenEntity = new AccessToken();
        accessTokenEntity.setAccess_token(accessToken);
        accessTokenEntity.setExpires_in(Long.valueOf(expiresSecond));
        accessTokenEntity.setToken_type("bearer");
        resultMsg = new ResultMsg(ResultStatusCode.OK.getCode(),ResultStatusCode.OK.getMsg(),accessTokenEntity);
        return resultMsg;
    }
}
