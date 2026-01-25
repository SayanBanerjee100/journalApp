package com.xyz.JournalApp1.journal.dto;

public class LoginRequest {

    private String emailId;
    private String password;

    public LoginRequest() {}

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
