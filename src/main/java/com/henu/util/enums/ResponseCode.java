package com.henu.util.enums;

/**
 * 响应类型
 */
public enum ResponseCode {


    错误(0,"ERROR"),
    成功(1,"SUCCESS"),
    异常(3,"EXCEPTION"),
    需要登陆(10,"NEED_LOGIN"),
    不合法的语法(2,"ILLEGAL_ARGIMENT");


    private int code;
    private String value;
    ResponseCode(int code,String value){
        this.code =code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
