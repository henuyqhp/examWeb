package com.henu.util.enums;


/**
 * 编辑考试类型
 */
public enum EditExam {
    上传试卷(0,"submit"),
    添加学生(1,"add_student"),
    开启考试(2,"start_exam")
    ;

    EditExam(int code,String value){
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
