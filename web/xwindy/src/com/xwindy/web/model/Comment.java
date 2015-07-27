package com.xwindy.web.model;

import java.io.Serializable;

import com.xwindy.web.util.SysUtil;

public class Comment implements Serializable{
    
    /**
     * 实例化id
     */
    private static final long serialVersionUID = -671976702682619841L;

    /**
     * 用户名
     */
    private String username;
    
    /**
     * 评论内容
     */
    private String content;
    
    /**
     * 字符串类型的时间
     */
    private String datetimeStr;
    
    /**
     * 评论表ID
     */
    private int id;
    
    /**
     * 用户表ID
     */
    private int userId;
    
    /**
     * 发布者IP地址
     */
    private String userIP;
    
    /**
     * 资讯表ID
     */
    private int newsId;
    
    /**
     * 资讯对象
     */
    private News news;
    
    /**
     * 发布时间，字符串类型
     */
    private String datetime;
    
    /**
     * 发布时间，长整型
     */
    private long datetimeLong;
    
    /**
     * 评论状态
     */
    private int state;
    
    
    /**
     * 评论对象的默认构造函数
     */
    public Comment() {}
    
    /**
     * 评论对象的构造函数
     * @param newsId - 新闻Id
     * @param content - 评论内容
     * @param userId - 用户Id
     * @param userIP - 用户IP
     * @param datetime - 评论时间
     */
    public Comment(int newsId, String content, int userId, String userIP, String datetime) {
        this.content = content;
        this.userId = userId;
        this.userIP = userIP;
        this.newsId = newsId;
        this.datetime = datetime;
    }





    /**
     * 取得用户名
     * @return - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * @param userName - 用户名
     */
    public void setUsername(String userName) {
        this.username = userName;
    }

    /**
     * 取得评论内容
     * @return - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     * @param content - 评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 取得字符串类型的时间
     * @return - 字符串类型的时间
     */
    public String getDatetimeStr() {
        return datetimeStr;
    }
    
    /**
     * 把字符类型的时间转化为yyyy-MM-dd HH:mm格式
     * @param datetime
     */
    public void setDatetimeStr(String datetime) {
        this.datetimeStr = SysUtil.long2DateStr("yyyy-MM-dd HH:mm", SysUtil.dateStr2Long(datetime));
    }
    
    /**
     * 得到评论表ID
     * @return - 评论表ID
     */
    public int getId() {
        return id;
    }

    /**
     * 设置评论表ID
     * @param id - 评论表ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 得到用户表ID
     * @return - 用户表ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 设置用户表ID
     * @param userId - 用户表ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    /**
     * 得到新闻表ID
     * @return - 新闻表ID
     */
    public int getNewsId() {
        return newsId;
    }

    /**
     * 设置新闻表ID
     * @param newsId -新闻表ID
     */
    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    /**
     * 得到资讯对象
     * @return 资讯对象
     */
    public News getNews() {
        return news;
    }

    /**
     * 设置资讯对象
     * @param news - 资讯对象
     */
    public void setNews(News news) {
        this.news = news;
    }

    /**
     * 得到发布者IP地址
     * @return - 发布者IP地址
     */
    public String getUserIP() {
        return userIP;
    }

    /**
     * 设置发布者IP地址
     * @param userIP - 发布者IP地址
     */
    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }
    
    /**
     * 得到发布时间，字符串类型
     * @return - 发布时间，字符串类型
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * 设置发布时间，字符串类型
     * @param datetime - 发布时间，字符串类型
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
        setDatetimeLong(datetime);
        setDatetimeStr(datetime);
    }

    /**
     * 得到发布时间，长整型
     * @return - 发布时间，长整型
     */
    public long getDatetimeLong() {
        return datetimeLong;
    }

    /**
     * 设置发布时间，长整型
     * @param datetime - 发布时间，长整型
     */
    public void setDatetimeLong(String datetime) {
        this.datetimeLong = SysUtil.dateStr2Long("yyyy-MM-dd HH:mm:ss", datetime);
    }

    /**
     * 获取评论状态
     * @return 评论状态
     */
    public int getState() {
        return state;
    }

    /**
     * 设置评论状态
     * @param state 评论状态值
     */
    public void setState(int state) {
        this.state = state;
    }

    

}
