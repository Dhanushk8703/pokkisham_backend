package com.pokkisham.app.controller;

import com.pokkisham.app.model.User;
import com.pokkisham.app.repository.UserRepository;
import com.pokkisham.app.security.JwtUtil;
import com.pokkisham.app.service.UserService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Map;
import com.pokkisham.app.model.Role;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User Created Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {

        User user = userRepository.findByUsername(loginUser.getUsername());

        if (user != null
                && passwordEncoder.matches(loginUser.getPassword(), user.getPassword())
                && user.getRole() == Role.ADMIN) {
            String accessToken = jwtUtil.generateAccessToken(user); // ✅ pass user, not username
            String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

            Map<String, String> response = new HashMap<>();
            response.put("accessToken", accessToken);
            response.put("refreshToken", refreshToken);
            response.put("role", user.getRole().name()); // ✅ optional but useful for frontend

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials or not admin");
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> req) {
        String refreshToken = req.get("refreshToken");

        try {
            String username = jwtUtil.extractUsername(refreshToken);

            // ✅ FIX (convert username → User)
            User user = userRepository.findByUsername(username);

            // Now this works because method expects User
            String newAccessToken = jwtUtil.generateAccessToken(user);

            Map<String, String> response = new HashMap<>();
            response.put("accessToken", newAccessToken);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }

}
