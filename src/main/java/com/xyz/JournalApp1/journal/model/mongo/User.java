package com.xyz.JournalApp1.journal.model.mongo;

import com.xyz.JournalApp1.journal.model.enums.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String name;
    private String emailId;
    private String password;

    private Role role = Role.USER;

    public User() {}

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmailId() { return emailId; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }




    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmailId(String emailId) { this.emailId = emailId; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(Role role) { this.role = role; }
}
