package com.xwindy.web.model;

import java.io.Serializable;

/**
 * 失物招领类
 * @author dremy
 *
 */
public class LostAndFound  implements Serializable{

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -683151812021533813L;

    /**
     * 失物招领表id
     */
    private int id;
    
    /**
     * 发布者id
     */
    private int sendId;
    
    /**
     * 发布用户
     */
    private User sendUser;
    
    /**
     * 发布者IP
     */
    private String sendIp;
    
    /**
     * 失误招领类型: 失为false, 拾为true
     */
    private boolean type;
    
    /**
     * 失/拾物地点
     */
    private String local;
    
    /**
     * 物品关键词
     */
    private String keyWord;
    
    /**
     * 发布时间
     */
    private String sendTime;
    
    /**
     * 失物简介
     */
    private String content;
    
    /**
     * 上传图片Url
     */
    private String picUrl;
    
    /**
     * 发布者电话
     */
    private String phone;
    
    /**
     * 招领状态: 1为进行中, 2为成功, 0为关闭
     */
    private int status;
    
    /**
     * 失物招领类的默认构造函数
     */
    public LostAndFound() {}
    
    /**
     * 失物招领类的一般构造函数
     */
    public LostAndFound(int sendId, String sendIp, boolean type, String local, String keyWord, String sendTime,
            String content, String picUrl, String phone) {
        this.sendId = sendId;
        this.sendIp = sendIp;
        this.type = type;
        this.local = local;
        this.keyWord = keyWord;
        this.sendTime = sendTime;
        this.content = content;
        this.picUrl = picUrl;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getSendIp() {
        return sendIp;
    }

    public void setSendIp(String sendIp) {
        this.sendIp = sendIp;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
}
