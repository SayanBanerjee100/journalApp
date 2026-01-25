package com.xyz.JournalApp1.journal.dto;

public class UserResponseDto {
    private String id;

    public String getUsername() {
        return username;
    }

    public String getEmailId() {
        return email;
    }

    private String username;
    private String email;

    public UserResponseDto() {}

    public UserResponseDto(String id, String username, String emailId) {
        this.id = id;
        this.username = username;
        this.email = emailId;
    }

    public String getId() {
        return id;
    }


}
