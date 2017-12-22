package com.henu.pojo;

import java.util.Date;

public class LogExamSubit {
    private Integer id;

    private Integer eid;

    private Integer sid;

    private String filename;

    private Date modifytime;

    private Date createtime;

    public LogExamSubit(Integer id, Integer eid, Integer sid, String filename, Date modifytime, Date createtime) {
        this.id = id;
        this.eid = eid;
        this.sid = sid;
        this.filename = filename;
        this.modifytime = modifytime;
        this.createtime = createtime;
    }

    public LogExamSubit() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
        return "LogExamSubit{" +
                "id=" + id +
                ", eid=" + eid +
                ", sid=" + sid +
                ", filename='" + filename + '\'' +
                ", modifytime=" + modifytime +
                ", createtime=" + createtime +
                '}';
    }
}