package com.szwx.yht.common;



 import java.util.Date;

/**
 * 查询记录公共类
 * @author zhangyj
 * @date Mar 29, 2012
 */
public class CommonQuery {
    private Date start;			//开始时间
    private Date end;			//截止时间
    private int pageIndex=1;		//当前页

    public static int pageSize=10;		//每页显示条目
    private int totalPage;		//总页数
    private int totalCount;		//总条数

    //********************SET　ＧＥＴ****************************//
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }
    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
    }
    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


}

