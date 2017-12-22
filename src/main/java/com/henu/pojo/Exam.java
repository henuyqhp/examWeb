package com.henu.pojo;

import java.util.Date;

public class Exam {
    private Integer id;

    private String ename;

    private String type;

    private Integer eenable;

    private Date starttime;

    private Date endtime;

    private Date modifytime;

    private Date createtime;

    private Integer ecreator;

    private Integer status;

    private String filename;

    public Exam(Integer id, String ename, String type, Integer eenable, Date starttime, Date endtime, Date modifytime, Date createtime, Integer ecreator, Integer status, String filename) {
        this.id = id;
        this.ename = ename;
        this.type = type;
        this.eenable = eenable;
        this.starttime = starttime;
        this.endtime = endtime;
        this.modifytime = modifytime;
        this.createtime = createtime;
        this.ecreator = ecreator;
        this.status = status;
        this.filename = filename;
    }

    public Exam() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getEenable() {
        return eenable;
    }

    public void setEenable(Integer eenable) {
        this.eenable = eenable;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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

    public Integer getEcreator() {
        return ecreator;
    }

    public void setEcreator(Integer ecreator) {
        this.ecreator = ecreator;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }
}