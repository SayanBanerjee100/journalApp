package com.xyz.JournalApp1.journal.controller;

import com.xyz.JournalApp1.journal.dto.LoginRequest;
import com.xyz.JournalApp1.journal.model.mongo.User;
import com.xyz.JournalApp1.journal.security.JwtUtil;
import com.xyz.JournalApp1.journal.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;




@Tag(name = "Auth", description = "Authentication APIs")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
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

    @PostMapping("/logout")
    public String logout() {
        return "Logged out successfully ✅";
    }
}


