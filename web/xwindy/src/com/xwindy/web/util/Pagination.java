package com.xwindy.web.util;


/**
 * 分页对象, 负责输出导航条
 * @author Dremy
 *
 */
public class Pagination {
    
    
    //以下是赋值
    /**
     * 当前页码
     */
    private int nowPage;
    
    /**
     * 总条数
     */
    private int sum;
    
    /**
     * 每页条数
     */
    private int pageSize;
    
    /**
     * 当前视图的最多页码显示数(最好为单数)
     */
    private int viewPageNum;
    
    
    
    //以下是计算
    /**
     * 最大页码
     */
    private int maxPage;
    
    /**
     * 当前视图的第一个页码
     */
    private int viewFirstPage;
    
    /**
     * 当前视图的最后一个页码
     */
    private int viewLastPage;
    
    /**
     * 是否显示向前按钮
     */
    private boolean preShow;
    
    /**
     * 是否显示向后按钮
     */
    private boolean nextShow;
    
    /**
     * 分页URL
     */
    private String pageUrl;

    /**
     * 默认构造函数
     */
    public Pagination() {}
    
    /**
     * 构造函数
     */
    public Pagination(String pageUrl, Page page, int sum, int viewPageNum) {
        this.pageUrl = pageUrl;
        this.nowPage = page.getPageNo();
        this.sum = sum;
        this.pageSize = page.getPageSize();
        this.viewPageNum = viewPageNum;
        this.maxPage = sum / pageSize + 1;
        calculateViewPage();
    }
    
    /**
     * 构造函数
     */
    public Pagination(String pageUrl, int nowPage, int pageSize, int sum, int viewPageNum) {
        this.pageUrl = pageUrl;
        this.nowPage = nowPage;
        this.sum = sum;
        this.pageSize = pageSize;
        this.viewPageNum = viewPageNum;
        this.maxPage = sum / pageSize + 1;
        calculateViewPage();
    }
    
    /**
     * 计算未传入的值
     */
    private void calculateViewPage() {

        boolean flagLeft = false;
        boolean flagRight = false;
        preShow = false;
        nextShow = false;
        
        if (viewPageNum >= maxPage) {
            viewFirstPage = 1;
            viewLastPage  = maxPage;
            return;
        }

        
        if (nowPage <= viewPageNum / 2 + 1) {
            viewFirstPage = 1;
            flagLeft = true;
        } else {
            viewFirstPage = nowPage - viewPageNum / 2;
        }
        
        if (nowPage + viewPageNum / 2 >= maxPage) {
            viewLastPage = maxPage;
            flagRight = true;
        } else {
            viewLastPage = nowPage + viewPageNum / 2;
        }
        
        if (!flagLeft && !flagRight) {
            preShow = true;
            nextShow = true;
            return;
        }
        
        if (flagLeft) {
            viewLastPage = viewPageNum;
            nextShow = true;
            return;
        }
        
        if (flagRight) {
            viewFirstPage = viewLastPage - viewPageNum + 1;
            preShow = true;
            return;
        }
        
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getViewPageNum() {
        return viewPageNum;
    }

    public void setViewPageNum(int viewPageNum) {
        this.viewPageNum = viewPageNum;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getViewFirstPage() {
        return viewFirstPage;
    }

    public void setViewFirstPage(int viewFirstPage) {
        this.viewFirstPage = viewFirstPage;
    }

    public int getViewLastPage() {
        return viewLastPage;
    }

    public void setViewLastPage(int viewLastPage) {
        this.viewLastPage = viewLastPage;
    }

    public boolean isPreShow() {
        return preShow;
    }

    public void setPreShow(boolean preShow) {
        this.preShow = preShow;
    }

    public boolean isNextShow() {
        return nextShow;
    }

    public void setNextShow(boolean nextShow) {
        this.nextShow = nextShow;
    }
    

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    
    
    
    
    
    
    
    
    
}
