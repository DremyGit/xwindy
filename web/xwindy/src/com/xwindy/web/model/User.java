package com.xwindy.web.model;

import java.io.Serializable;

/**
 * 用户类
 * @author Dremy
 *
 */
public class User implements Serializable{

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1623484656491311326L;

    /**
     * 用户id
     */
    private int id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;        
    
    /**
     * 用户类型
     */
    protected String userType;
    
    /**
     * 电话号码
     */
    private String telNumber;
    
    /**
     * email
     */
    private String email;
    
    /**
     * 最后活动时间
     */
    private String lastActive;
    
    /**
     * 头像Url
     */
    private String header;
    
    /**
     * 用户账号状态
     */
    private int state;
    
    /**
     * 用户类的默认构造函数
     */
    public User() {}
    
    /**
     * 用户类的构造函数
     */
    public User(String username, String password, String userType,
            String telNumber, String email, String header) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.telNumber = telNumber;
        this.email = email;
        this.header = header;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastActive() {
        return lastActive;
    }

    public void setLastActive(String lastActive) {
        this.lastActive = lastActive;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    
    
}
