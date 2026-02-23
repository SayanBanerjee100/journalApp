package com.xyz.JournalApp1.journal.service;

import com.xyz.JournalApp1.journal.model.enums.Role;
import com.xyz.JournalApp1.journal.model.mongo.User;
import com.xyz.JournalApp1.journal.repository.mongo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
    public User getByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new RuntimeException("User not found with emailId: " + emailId));
    }

    public User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User save(User user) {
        return userRepository.save(user);
    }



}
