package com.henu.util;
import com.github.pagehelper.Page;

import java.util.List;

public class PageInfo<T> {
    private int pages;//总页数
    private int pageSize;//每页显示的个数
    private List<T> list;//总数据
    private int total;//数据总个数
    private int start;//数据开始查找
    private int end;//数据结束查找
    private int pageNum;

    public PageInfo(int pageSize,int total,int pageNum){
        this.list = list;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        int i = this.total % this.pageSize;
        if (i == 0){
            this.pages = this.total / this.pageSize;
        }else{
            this.pages = this.total / this.pageSize + 1;
        }
        start = (pageNum -1)* pageSize;
        if(pageNum * pageSize >= total){
            end = total + 1;
        }else{
            end = pageSize + start;
        }
        System.out.println(this.toString());
    }


    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pages=" + pages +
                ", pageSize=" + pageSize +
                ", list=" + list +
                ", total=" + total +
                ", start=" + start +
                ", end=" + end +
                ", pageNum=" + pageNum +
                '}';
    }
}