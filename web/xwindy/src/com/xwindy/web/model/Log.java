package com.xwindy.web.model;

public class Log {
    
    private int id;
    
    private int userId;
    
    private String userIp;
    
    private String content;
    
    private String datetime;
    
    public Log() {}
    
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
    
    
    
    
}