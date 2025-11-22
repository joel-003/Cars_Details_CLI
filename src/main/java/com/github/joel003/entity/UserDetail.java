package com.github.joel003.entity;

public class UserDetail {

    private int u_id;
    private String u_name;
    private String u_email;
    private String u_phoneNumber;
    private String u_address;
    private String pwd;

    public int getU_id() {
        return u_id;
    }
    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
    public String getU_name() {
        return u_name;
    }
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }
    public String getU_email() {
        return u_email;
    }
    public void setU_email(String u_email) {
        this.u_email = u_email;
    }
    public String getU_phoneNumber() {
        return u_phoneNumber;
    }
    public void setU_phoneNumber(String phno) {
        this.u_phoneNumber = phno;
    }
    public String getU_address() {
        return u_address;
    }
    public void setU_address(String u_address) {
        this.u_address = u_address;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
