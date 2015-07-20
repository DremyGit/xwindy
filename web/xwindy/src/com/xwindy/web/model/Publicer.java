package com.xwindy.web.model;

public class Publicer extends User {
    
    private int publicClass;
    
    private String introduce;
    
    public Publicer() {}
    
    public Publicer(String username, String password, String userType,
            String telNumber, String email, String header,
            int publicClass, String introduce) {
        super(username, password, userType, telNumber, email, header);
        this.publicClass = publicClass;
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "Publicer [publicClass=" + publicClass + ", introduce=" + introduce + ", userType=" + userType
                + ", getPublicClass()=" + getPublicClass() + ", getIntroduce()=" + getIntroduce() + ", getId()="
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
    
    

}
