package com.xwindy.web.util;

/**
 * 分页对象
 * @author Administrator
 *
 */
public class Page {

	//默认每页10条
	public static final int DEFAULT_PAGESIZE=10;
	
	//页码,从1开始
	private int pageNo=1;
	
	//每页记录数
	private int pageSize=DEFAULT_PAGESIZE;
	
	//总记录数
	private int totalCount;
	
	//排序字段
	private String orderBy;

    /**
     * Page类的默认构造函数
     */
    public Page() {};
    
    /**
     * Page类构造函数
     * @param pageNo
     */
    public Page(int pageNo) {
    	this.pageNo = pageNo;
    }
    
    /**
     * Page类构造函数
     * @param pageNo
     * @param pageSize
     */
    public Page(int pageNo, int pageSize) {
        this.pageNo     = pageNo;
        this.pageSize   = pageSize;
    }
    
    /**
     * Page类构造函数
     * @param pageNo
     * @param pageSize
     * @param orderBy
     */
    public Page(int pageNo, int pageSize, String orderBy) {
        this.pageNo     = pageNo;
        this.pageSize   = pageSize;
        this.orderBy    = orderBy;
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	
}
