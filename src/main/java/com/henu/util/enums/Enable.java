package com.henu.util.enums;

/**
 * 是否可用类型
 */
public enum Enable {
    可用(1,"use"),
    不可用(0,"nouse")
    ;


    Enable(int code,String value){
        this.code = code;
        this.value = value;
    }
    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
