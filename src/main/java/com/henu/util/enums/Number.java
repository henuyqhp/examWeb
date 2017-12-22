package com.henu.util.enums;

/**
 * 数字类型
 */
public enum Number {

    ZEOR(0,"ZEOR"),
    ONE(1,"ONE"),

    ;

    Number(int code,String value){
        this.code = code;
        this.value =value;
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
