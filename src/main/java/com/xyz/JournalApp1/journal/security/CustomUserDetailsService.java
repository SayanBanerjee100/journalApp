package com.xyz.JournalApp1.journal.security;

import com.xyz.JournalApp1.journal.model.mongo.User;
import com.xyz.JournalApp1.journal.repository.mongo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        User user = userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with emailId: " + emailId));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmailId())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
