package com.xyz.JournalApp1.journal.security;

import com.xyz.JournalApp1.journal.model.Role;
import com.xyz.JournalApp1.journal.model.User;
import com.xyz.JournalApp1.journal.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler extends org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public OAuth2SuccessHandler(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        if (email == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Email not found from Google OAuth2");
            return;
        }

        // ✅ Create user if first time login
        User user = userRepository.findByEmailId(email).orElse(null);

        if (user == null) {
            User newUser = new User();
            newUser.setName(name != null ? name : "Google User");
            newUser.setEmailId(email);

            // ✅ password not required for Google login
            newUser.setPassword("GOOGLE_AUTH");
            newUser.setRole(Role.USER);

            user = userRepository.save(newUser);
        }

        // ✅ Generate YOUR JWT
        String token = jwtUtil.generateToken(user.getEmailId(), user.getRole().name());

        // ✅ Return token (backend-only)
        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
    }
}
