package com.example.ucenter.utils;

import lombok.Data;

/**
 * Created by admin on 2018/3/30.
 */
@Data
public class ResultMsg {
    private  int errcode;

    private  String errmsg;

    private Object p2pdata;

    public ResultMsg(int ErrCode, String ErrMsg, Object P2pData)
   {
       this.errcode = ErrCode;
       this.errmsg = ErrMsg;
       this.p2pdata = P2pData;
   }
}
