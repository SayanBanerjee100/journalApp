package com.xyz.JournalApp1.journal.service;

import com.xyz.JournalApp1.journal.model.User;
import com.xyz.JournalApp1.journal.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomerDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmailId(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmailId())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
