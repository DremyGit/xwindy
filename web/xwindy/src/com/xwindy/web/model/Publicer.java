package com.xwindy.web.model;

import java.io.Serializable;

/**
 * 公众号用户类
 * @author Dremy
 *
 */
public class Publicer extends User implements Serializable{
    
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 5667052754115977287L;

    /**
     * 公众号分类id
     */
    private int publicClass;
    
    /**
     * 简介
     */
    private String introduce;
    
    /**
     * 是否已被用户订阅
     */
    private boolean isSub;
    
    /**
     * 资讯号发布的资讯总条数
     */
    private int newsNumber;
    
    /**
     * 资讯号最后一条资讯的发布时间
     */
    private String lastNewsTime;
    
    /**
     * 资讯号粉丝数
     */
    private int subscribeUserNum;
    
    /**
     * 报修号总接受报修数
     */
    private int repairTotalNum;
    
    /**
     * 报修号尚未处理条数
     */
    private int repairNotFinishNum;
    
    /**
     * 公众号类默认构造函数
     */
    public Publicer() {}
    
    /**
     * 公众号类构造函数
     */
    public Publicer(String username, String password, String userType,
            String telNumber, String email, String header,
            int publicClass, String introduce) {
        super(username, password, userType, telNumber, email, header);
        this.publicClass = publicClass;
        this.introduce = introduce;
    }

    /**
     * 重写toString方法
     */
    @Override
    public String toString() {
        return "Publicer [publicClass=" + publicClass + ", introduce=" + introduce + ", userType=" + userType
                + ", isSub=" + isSub + ", getPublicClass()=" + getPublicClass() + ", getIntroduce()=" + getIntroduce() + ", getId()="
                + getId() + ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getUserType()="
                + getUserType() + ", getTelNumber()=" + getTelNumber() + ", getEmail()=" + getEmail()
                + ", getLastActive()=" + getLastActive() + ", getHeader()=" + getHeader() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

    public int getPublicClass() {
        return publicClass;
    }

    public void setPublicClass(int publicClass) {
        this.publicClass = publicClass;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public boolean isIsSub() {
        return isSub;
    }

    public void setIsSub(boolean isSub) {
        this.isSub = isSub;
    }

    public int getNewsNumber() {
        return newsNumber;
    }

    public void setNewsNumber(int newsNumber) {
        this.newsNumber = newsNumber;
    }

    public String getLastNewsTime() {
        return lastNewsTime;
    }

    public void setLastNewsTime(String lastNewsTime) {
        this.lastNewsTime = lastNewsTime;
    }

    public int getSubscribeUserNum() {
        return subscribeUserNum;
    }

    public void setSubscribeUserNum(int subscribeUserNum) {
        this.subscribeUserNum = subscribeUserNum;
    }

    public int getRepairTotalNum() {
        return repairTotalNum;
    }

    public void setRepairTotalNum(int repairTotalNum) {
        this.repairTotalNum = repairTotalNum;
    }

    public int getRepairNotFinishNum() {
        return repairNotFinishNum;
    }

    public void setRepairNotFinishNum(int repairNotFinishNum) {
        this.repairNotFinishNum = repairNotFinishNum;
    }
    
    

}
