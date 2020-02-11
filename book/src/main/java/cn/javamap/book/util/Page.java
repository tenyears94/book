package cn.javamap.book.util;

import org.springframework.stereotype.Component;

public class Page {
    private long totalNum;   //查询总共记录数
    private int pageCount;  //总共页数
    private int pageNo;     //当前页数
    private int pageSize;   //每页数量

    public Page() {
        this.pageNo = 1;   //初始化当前页面为第一页
        this.pageSize = 5;  //初始化每页个数5个
    }

    public Page(long totalNum, int pageCount, int pageNo, int pageSize) {
        this.totalNum = totalNum;
        this.pageCount = pageCount;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalNum=" + totalNum +
                ", pageCount=" + pageCount +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
