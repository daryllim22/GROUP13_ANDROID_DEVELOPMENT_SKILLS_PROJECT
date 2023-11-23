package com.inti.atv_assignment;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String name;
    private String email;
    private String role;
    private String status;
    private int userDBID;
    private String phoneNum;

    //Constructor
    public User(String username, String password, String name, String email, String role, String status, int userDBID, String phoneNum) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
        this.userDBID = userDBID;
        this.phoneNum = phoneNum;
    }

    //Default Constructor
    public User() {
        this.username = "";
        this.password = "";
        this.name = "";
        this.email = "";
        this.role = "";
        this.status = "";
        this.userDBID = 0;
        this.phoneNum = "";
    }

    //Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public int getUserDBID() {
        return userDBID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserDBID(int userDBID) {
        this.userDBID = userDBID;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
