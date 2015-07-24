package com.xwindy.web.model;

import java.io.Serializable;

/**
 * 报修类
 * @author Dremy
 *
 */
public class Repair implements Serializable{
    
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6442776189628449650L;

    /**
     * 报修表中的id
     */
    private int id;
    
    /**
     * 报修学生id
     */
    private int studentId;
    
    /**
     * 报修学生对象
     */
    private Student student;
    
    /**
     * 报修号id
     */
    private int repairerId;
    
    /**
     * 报修号对象
     */
    private Publicer repairer;
    
    /**
     * 报修地点
     */
    private String local;
    
    /**
     * 报修内容
     */
    private String content;
    
    /**
     * 报修者电话
     */
    private String phone;
    
    /**
     * 提交时间
     */
    private String datetime;
    
    /**
     * 报修状态: 0为待处理, 1为已处理, 2为拒绝处理
     */
    private int status;
    
    /**
     * 处理时间
     */
    private String resolvetime;

    /**
     * 报修类的默认构造函数
     */
    public Repair() {}
    
    /**
     * 报修类的构造函数
     */
    public Repair(int studentId, int repairerId, String local, String content, String phone, String datetime) {
        this.studentId = studentId;
        this.repairerId = repairerId;
        this.local = local;
        this.content = content;
        this.phone = phone;
        this.datetime = datetime;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getRepairerId() {
        return repairerId;
    }

    public void setRepairerId(int repairerId) {
        this.repairerId = repairerId;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResolvetime() {
        return resolvetime;
    }

    public void setResolvetime(String resolvetime) {
        this.resolvetime = resolvetime;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Publicer getRepairer() {
        return repairer;
    }

    public void setRepairer(Publicer repairer) {
        this.repairer = repairer;
    }

    

}
