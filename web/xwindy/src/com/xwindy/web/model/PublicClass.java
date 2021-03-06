package com.xwindy.web.model;

import java.io.Serializable;

/**
 * 公众号分类
 * @author Dremy
 *
 */
public class PublicClass implements Serializable{

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 5055287158557870005L;

    private int id;
    
    private String className;
    
    private int level;
    
    private int order;
    
    public PublicClass() {};
    

    public PublicClass(int id, String className, int level, int order) {
        this.id = id;
        this.className = className;
        this.level = level;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
    
}
