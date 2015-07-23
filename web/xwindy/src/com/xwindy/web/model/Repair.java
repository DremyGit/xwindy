package com.xwindy.web.model;

/**
 * 报修类
 * @author Dremy
 *
 */
public class Repair {
    
    /**
     * 报修表中的id
     */
    private int id;
    
    /**
     * 报修学生id
     */
    private int studentId;
    
    /**
     * 报修学生学号(外联)
     */
    private String studentNumber;
    
    /**
     * 学生用户名(外联)
     */
    private String studentUsername;
    
    /**
     * 报修号id
     */
    private int repairerId;
    
    /**
     * 报修号用户名(外联)
     */
    private String repairerName;
    
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
     * 报修状态
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

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getRepairerName() {
        return repairerName;
    }

    public void setRepairerName(String repairerName) {
        this.repairerName = repairerName;
    }
    
    
    

}
