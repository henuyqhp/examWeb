package com.henu.pojo;

public class Bingding {
    private Integer id;

    private Integer sid;

    private Integer eid;

    private String ip;

    public Bingding(Integer id, Integer sid, Integer eid, String ip) {
        this.id = id;
        this.sid = sid;
        this.eid = eid;
        this.ip = ip;
    }

    public Bingding() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}