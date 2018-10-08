package com.lizikj.common.util;

import java.io.Serializable;
/**
 * 分页参数
 * 
 * @author liaojw 
 * @date 2017年7月10日 下午2:06:44
 */
public class PageParameter implements Serializable{
	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_PAGE_SIZE = 20;
  
    private int pageSize;
     
    private int pageNum;
     
    private int totalCount;
     
    private int nextPage;
     
    private int prePage;
     
    private int totalPages;
     
    private int startRow;
    
    private String orderBy;
  
    public PageParameter() {
        this.pageNum = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }
  
    public PageParameter(int pageNo, int pageSize) {
        this.pageNum = pageNo;
        this.pageSize = pageSize;
    }
  
    public PageParameter(int pageNo, int pageSize, String orderBy) {
    	this.pageNum = pageNo;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }
    public int getPageSize() {
        return pageSize;
    }
 
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
 
    public int getPageNum() {
        return pageNum;
    }
 
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
        setStartRow(getComputeStartRow());
    }
 
    public int getTotalCount() {
        return totalCount;
    }
 
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        setTotalPages(getComputeTotalPages());
        setPrePage(getComputePrePage());
        setNextPage(getComputeNextPage());
    }
 
    public int getNextPage() {
        return nextPage;
    }
 
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
 
    public int getPrePage() {
        return prePage;
    }
 
    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }
 
    public int getTotalPages() {
        return totalPages;
    }
 
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
 
    public int getStartRow() {
        return startRow;
    }
 
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
    
    public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	//开始读取的行数
    public int getComputeStartRow() {
        return (this.pageNum-1)*this.pageSize;
    }
     
    //总页数
    public int getComputeTotalPages() {
        int page = this.totalCount/this.pageSize;
        if(this.totalCount%this.pageSize!=0){
            page++;
        }
        return page;
    }   
     
    //前一页的页码
    public int getComputePrePage() {
        int page = this.pageNum;
        page--;
        if(page<1){
            page++;
        }
        return page;
    }
     
    //后一页的页码
    public int getComputeNextPage() {
        int page = this.pageNum;
        page++;
        if(page>this.getTotalPages()){
            page--;
        }
        return page;
    }

    @Override
    public String toString() {
        return "PageParameter{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", totalCount=" + totalCount +
                ", nextPage=" + nextPage +
                ", prePage=" + prePage +
                ", totalPages=" + totalPages +
                ", startRow=" + startRow +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }
}