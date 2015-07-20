package com.xwindy.web.model;


public class User {

    private int id;
    
    private String username;
    
    private String password;        
    
    protected String userType;
    
    private String telNumber;
    
    private String email;
    
    private String lastActive;
    
    private String header;
    
    public User() {}
    
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

    
    
}
