package com.henu.vo;

import com.henu.pojo.Student;
import com.henu.pojo.Teacher;
import com.henu.util.enums.UserType;

public class User {
    private Integer id;
    private Integer no;
    private String name;
    private Integer admin;
    private Integer type;
    private String ip;

    public User(){}
    public User(Teacher teacher){
        this.id = teacher.getId();
        this.no = teacher.getTno();
        this.admin = teacher.getTadmin();
        this.name = teacher.getTname();
        this.type = teacher.getTadmin() == 0 ? UserType.教师.getCode() : UserType.管理员.getCode();
    }

    public User(Student student){
        this.id = student.getId();
        this.no = student.getSno();
        this.name = student.getSname();
        this.ip = student.getIp();
        this.type = UserType.学生.getCode();
    }

    public User(Integer id, Integer no, String name, Integer admin, Integer type, String ip) {
        this.id = id;
        this.no = no;
        this.name = name;
        this.admin = admin;
        this.type = type;
        this.ip = ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
