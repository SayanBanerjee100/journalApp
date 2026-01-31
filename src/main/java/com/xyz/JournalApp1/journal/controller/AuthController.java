package com.xyz.JournalApp1.journal.controller;

import com.xyz.JournalApp1.journal.dto.LoginRequest;
import com.xyz.JournalApp1.journal.model.mongo.User;
import com.xyz.JournalApp1.journal.security.JwtUtil;
import com.xyz.JournalApp1.journal.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.xyz.JournalApp1.journal.dto.ForgotPasswordRequest;
import com.xyz.JournalApp1.journal.dto.ResetPasswordRequest;
import com.xyz.JournalApp1.journal.service.RedisTokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;



@Tag(name = "Auth", description = "Authentication APIs")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final RedisTokenBlacklistService tokenBlacklistService;



    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, RedisTokenBlacklistService tokenBlacklistService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailId(),
                        request.getPassword()
                )
        );

        User user = userService.getByEmailId(request.getEmailId());

        return jwtUtil.generateToken(user.getEmailId(), user.getRole().name());
    }
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody ForgotPasswordRequest request) {
        userService.generateAndSendResetOtp(request.getEmailId());
        return "OTP sent to email ✅";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ResetPasswordRequest request) {
        userService.resetPassword(
                request.getEmailId(),
                request.getOtp(),
                request.getNewPassword()
        );
        return "Password reset successful ✅";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return "No token provided";
        }

        String token = authHeader.substring(7);

        tokenBlacklistService.blacklistToken(token, jwtUtil.extractExpiration(token));

        return "Logged out successfully ✅";
    }


}
