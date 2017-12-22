package com.henu.pojo;

import java.util.Date;

public class Student {
    private Integer id;

    private Integer sno;

    private String sname;

    private String spass;

    private String ip;

    private Integer enable;

    private String classnum;

    private Date modifytime;

    private Date createtime;

    public Student(Integer id, Integer sno, String sname, String spass, String ip, Integer enable, String classnum, Date modifytime, Date createtime) {
        this.id = id;
        this.sno = sno;
        this.sname = sname;
        this.spass = spass;
        this.ip = ip;
        this.enable = enable;
        this.classnum = classnum;
        this.modifytime = modifytime;
        this.createtime = createtime;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getSpass() {
        return spass;
    }

    public void setSpass(String spass) {
        this.spass = spass == null ? null : spass.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getClassnum() {
        return classnum;
    }

    public void setClassnum(String classnum) {
        this.classnum = classnum == null ? null : classnum.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}