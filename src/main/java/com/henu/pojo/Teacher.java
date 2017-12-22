package com.henu.pojo;

import java.util.Date;

public class Teacher {
    private Integer id;

    private Integer tno;

    private String tname;

    private String tpass;

    private Integer enable;

    private Integer tadmin;

    private Date modifytime;

    private Date createtime;

    public Teacher(Integer id, Integer tno, String tname, String tpass, Integer enable, Integer tadmin, Date modifytime, Date createtime) {
        this.id = id;
        this.tno = tno;
        this.tname = tname;
        this.tpass = tpass;
        this.enable = enable;
        this.tadmin = tadmin;
        this.modifytime = modifytime;
        this.createtime = createtime;
    }

    public Teacher() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public String getTpass() {
        return tpass;
    }

    public void setTpass(String tpass) {
        this.tpass = tpass == null ? null : tpass.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getTadmin() {
        return tadmin;
    }

    public void setTadmin(Integer tadmin) {
        this.tadmin = tadmin;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", tno=" + tno +
                ", tname='" + tname + '\'' +
                ", tpass='" + tpass + '\'' +
                ", enable=" + enable +
                ", tadmin=" + tadmin +
                ", modifytime=" + modifytime.toString() +
                ", createtime=" + createtime.toString() +
                '}';
    }
}