package com.xyz.JournalApp1.journal.controller;

import com.xyz.JournalApp1.journal.model.AuditLog;
import com.xyz.JournalApp1.journal.model.Role;
import com.xyz.JournalApp1.journal.model.User;
import com.xyz.JournalApp1.journal.repository.AuditLogRepository;
import com.xyz.JournalApp1.journal.repository.UserRepository;
import com.xyz.JournalApp1.journal.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin", description = "Admin-only APIs")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuditLogRepository auditLogRepository;




    public AdminController(UserService userService, UserRepository userRepository, AuditLogRepository auditLogRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.auditLogRepository =auditLogRepository;
    }

    @GetMapping("/health")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminHealth() {
        return "ADMIN API is working ✅";
    }

    @GetMapping("/audit-logs")
    @PreAuthorize("hasRole('ADMIN')")
    public List<AuditLog> getAuditLogs() {
        return auditLogRepository.findAll();
    }



    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/make-admin/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public User makeAdmin(@PathVariable String userId) {
        User user = userService.getById(userId);
        user.setRole(Role.ADMIN);
        return userService.save(user);
    }
}
