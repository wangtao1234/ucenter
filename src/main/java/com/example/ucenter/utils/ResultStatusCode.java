package com.example.ucenter.utils;

/**
 * Created by admin on 2018/3/30.
 */
public enum  ResultStatusCode {
    OK(0,"OK"),
    SYSTEM_ERR(30001, "System error"),
    INVALID_CLIENTID(1001,"invalid clientid"),
    INVALID_PASSWORD(1002,"invalid password"),
    INVALID_TOKEN(1003,"invalid token"),
    PERMISSION_DENIED(1004,"permission denied")
    ;

    private int code;
    private String msg;

    ResultStatusCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
