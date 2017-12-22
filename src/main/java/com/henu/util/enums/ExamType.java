package com.henu.util.enums;

public enum ExamType {

    Java(0,"JAVA"),
    C(1,"C"),
    CPP(2,"C++"),
    CSHARP(3,"C#"),
    Android(4,"Android")
    ;
    private int code;
    private String value;

    ExamType(int code,String value){
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
