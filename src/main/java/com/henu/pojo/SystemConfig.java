package com.henu.pojo;

public class SystemConfig {
    private Integer systemid;

    private Integer interval;

    private Integer pagequery;

    private Integer threshold;

    private Integer superiorsize;

    private Integer limitsize;

    private Integer teacheradmin;

    public SystemConfig(Integer systemid, Integer interval, Integer pagequery, Integer threshold, Integer superiorsize, Integer limitsize, Integer teacheradmin) {
        this.systemid = systemid;
        this.interval = interval;
        this.pagequery = pagequery;
        this.threshold = threshold;
        this.superiorsize = superiorsize;
        this.limitsize = limitsize;
        this.teacheradmin = teacheradmin;
    }

    public SystemConfig() {
        super();
    }

    public Integer getSystemid() {
        return systemid;
    }

    public void setSystemid(Integer systemid) {
        this.systemid = systemid;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getPagequery() {
        return pagequery;
    }

    public void setPagequery(Integer pagequery) {
        this.pagequery = pagequery;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getSuperiorsize() {
        return superiorsize;
    }

    public void setSuperiorsize(Integer superiorsize) {
        this.superiorsize = superiorsize;
    }

    public Integer getLimitsize() {
        return limitsize;
    }

    public void setLimitsize(Integer limitsize) {
        this.limitsize = limitsize;
    }

    public Integer getTeacheradmin() {
        return teacheradmin;
    }

    public void setTeacheradmin(Integer teacheradmin) {
        this.teacheradmin = teacheradmin;
    }
}