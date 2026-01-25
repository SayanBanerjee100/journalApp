package com.xyz.JournalApp1.journal.dto;

public class ResetPasswordRequest {

    private String emailId;
    private String otp;
    private String newPassword;

    public ResetPasswordRequest() {}

    public String getEmailId() {
        return emailId;
    }

    public String getOtp() {
        return otp;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
