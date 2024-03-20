package com.example.spring_boot_leson.service;

import com.example.spring_boot_leson.config.jwt.JwtUtils;
import com.example.spring_boot_leson.model.Role;
import com.example.spring_boot_leson.model.User;
import com.example.spring_boot_leson.repository.UserRepository;
import com.example.spring_boot_leson.request.LoginRequest;
import com.example.spring_boot_leson.request.UserRegisterRequest;
import com.example.spring_boot_leson.response.JWTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public JWTResponse registerUser(UserRegisterRequest userRegisterRequest) {
        User user = new User(userRegisterRequest.getEmail());
        user.setEmail( userRegisterRequest.getEmail() );
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        if (repository.existsByEmail(userRegisterRequest.getEmail()))
            throw new RuntimeException("The email " + userRegisterRequest.getEmail() + " is already in use!");

        User savedUser = repository.save(user);
        String token = jwtUtils.generateToken(userRegisterRequest.getEmail());

        return new JWTResponse(
                savedUser.getEmail(),
                token,
                "Dastan",
                savedUser.getRole()

        );
}
    public JWTResponse authenticate(LoginRequest loginRequest) {
        User user = repository.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
                new RuntimeException("User with email: " + loginRequest.getEmail() + " not found!"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtils.generateToken(user.getEmail());
        return new JWTResponse(
                user.getEmail(),
                token,
                "Dastan",
                user.getRole()

        );
    }
}