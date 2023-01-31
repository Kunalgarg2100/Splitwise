package org.splitwise.model;

public class User {
    private String userID;
    private String name;
    private String email;
    private int mobileNum;

    public User( String userID, String name, String email, int mobileNum) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.mobileNum = mobileNum;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(int mobileNum) {
        this.mobileNum = mobileNum;
    }
}
