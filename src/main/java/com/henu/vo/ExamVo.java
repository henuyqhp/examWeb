package com.henu.vo;

import com.henu.util.DateTimeUtil;

import java.util.Date;

public class ExamVo {

    private Integer id;
    private String ename;
    private String type;
    private Integer eCreator;
    private String filename;
    private Date startTime;
    private Date endTime;
    private Integer status;

    public ExamVo() {
    }

    public ExamVo(Integer id, String ename, String type, Integer eCreator, String filename, Date startTime, Date endTime, Integer status) {
        this.id = id;
        this.ename = ename;
        this.type = type;
        this.eCreator = eCreator;
        this.filename = filename;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        System.out.println(DateTimeUtil.strToDate("2017-12-10 16:16:54"));
//        System.out.println(date.toString());
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
        this.ename = ename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer geteCreator() {
        return eCreator;
    }

    public void seteCreator(Integer eCreator) {
        this.eCreator = eCreator;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExamVo{" +
                "id=" + id +
                ", ename='" + ename + '\'' +
                ", type='" + type + '\'' +
                ", eCreator=" + eCreator +
                ", filename='" + filename + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                '}';
    }
}
