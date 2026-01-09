package com.xyz.JournalApp1.journal.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    private String name;
    @NonNull
    private String password;
    @NonNull
    private String email;
}
