package com.xyz.JournalApp1.journal.service;

import com.xyz.JournalApp1.journal.model.Role;
import com.xyz.JournalApp1.journal.model.User;
import com.xyz.JournalApp1.journal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisOtpService redisOtpService;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, RedisOtpService redisOtpService) {
        this.redisOtpService = redisOtpService;
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
    public void generateAndSendResetOtp(String emailId) {

        User user = getByEmailId(emailId);

        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);

        redisOtpService.saveOtp(emailId, otp);

        emailService.sendOtpEmail(emailId, otp);
    }


    public void resetPassword(String emailId, String otp, String newPassword) {

        User user = getByEmailId(emailId);

        String savedOtp = redisOtpService.getOtp(emailId);

        if (savedOtp == null) {
            throw new RuntimeException("OTP expired or not generated");
        }

        if (!savedOtp.equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        redisOtpService.deleteOtp(emailId);
    }


}
