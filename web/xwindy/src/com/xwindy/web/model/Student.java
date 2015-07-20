package com.xwindy.web.model;

public class Student extends User {

    private String schoolNumber;
    
    private String sportPass;
    
    private String libPass;
    
    public Student() {}
    
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


    

}
