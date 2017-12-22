package com.henu.util.enums;

public enum ExamStatus {
    未开始(0,"unstart"),
    正在进行中(1,"start"),
    考试完成(2,"complete"),
    考试终止(3,"stop")
    ;


    private int code;
    private String value;

    ExamStatus(int code,String value){
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
