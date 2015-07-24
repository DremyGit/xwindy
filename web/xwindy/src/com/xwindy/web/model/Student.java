package com.xwindy.web.model;


/**
 * 学生类
 * @author Dremy
 *
 */
public class Student extends User {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 8968228686872774234L;

    /**
     * 学生学号
     */
    private String schoolNumber;
    
    /**
     * 学生体育查询密码
     */
    private String sportPass;
    
    /**
     * 学生图书馆查询密码
     */
    private String libPass;
    
    /**
     * 发表总评论数
     */
    private int commentNum;
    
    /**
     * 订阅公众号数
     */
    private int subscribeNum;
    
    /**
     * 学生类的默认构造函数
     */
    public Student() {}
    
    /**
     * 学生类的构造函数
     */
    public Student(String username, String password, 
            String telNumber, String email, String header,
            String schoolNumber, String sportPass, String libPass) {
        super(username, password, "XS", telNumber, email, header);
        this.schoolNumber = schoolNumber;
        this.sportPass = sportPass;
        this.libPass = libPass;
    }

    @Override
    public String toString() {
        return "Student [schoolNumber=" + schoolNumber + ", sportPass=" + sportPass + ", libPass=" + libPass
                + ", userType=" + userType + ", getSchoolNumber()=" + getSchoolNumber() + ", getSportPass()="
                + getSportPass() + ", getLibPass()=" + getLibPass() + ", getId()=" + getId() + ", getUsername()="
                + getUsername() + ", getPassword()=" + getPassword() + ", getUserType()=" + getUserType()
                + ", getTelNumber()=" + getTelNumber() + ", getEmail()=" + getEmail() + ", getLastActive()="
                + getLastActive() + ", getHeader()=" + getHeader() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }


    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }


    public String getSportPass() {
        return sportPass;
    }

    public void setSportPass(String sportPass) {
        this.sportPass = sportPass;
    }

    public String getLibPass() {
        return libPass;
    }

    public void setLibPass(String libPass) {
        this.libPass = libPass;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getSubscribeNum() {
        return subscribeNum;
    }

    public void setSubscribeNum(int subscribeNum) {
        this.subscribeNum = subscribeNum;
    }


    

}
