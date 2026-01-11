package com.xyz.JournalApp1.journal.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.xyz.JournalApp1.journal.model.User;

import com.xyz.JournalApp1.journal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getId() == null) {
            user.setId("USER");
        }

        return userRepository.save(user);
    }

    public UserService(UserRepository us, PasswordEncoder passwordEncoder) {

        this.userRepository = us;
        this.passwordEncoder = passwordEncoder;
    }

    public User createEntry(User entry) {
        return userRepository.save(entry);
    }

    public List<User> getAllEntries() {
        return userRepository.findAll();
    }

    public User getEntryById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteEntry(String id) {
        userRepository.deleteById(id);
    }
}
