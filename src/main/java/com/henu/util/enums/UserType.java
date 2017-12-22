package com.henu.util.enums;

/**
 * 用户类型
 */
public enum UserType {

    学生(0,"STUDENT"),
    教师(1,"TEACHER"),
    管理员(2,"ADMIN");

    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    UserType(int code, String value){
        this.code = code;
        this.value = value;

    }
}
