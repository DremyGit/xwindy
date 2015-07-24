package com.xwindy.web.model;

import java.io.Serializable;

/**
 * 系统日志类
 * @author Dremy
 *
 */
public class Log implements Serializable{
    
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 3874291759462285769L;

    /**
     * 日志id
     */
    private int id;
    
    /**
     * 产生日志的用户id
     */
    private int userId;
    
    /**
     * 产生日志的用户对象
     */
    private User user;
    
    /**
     * 产生日志的用户ip
     */
    private String userIp;
    
    /**
     * 日志记录内容
     */
    private String content;
    
    /**
     * 日志记录时间
     */
    private String datetime;
    
    /**
     * 日志类的默认构造函数
     */
    public Log() {}
    
    /**
     * 日志类的构造函数
     */
    public Log(int userId, String userIp, String content, String datetime) {
        this.userId = userId;
        this.userIp = userIp;
        this.content = content;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
    
}