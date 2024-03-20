package com.example.spring_boot_leson.service;



import com.example.spring_boot_leson.config.jwt.JwtUtils;
import com.example.spring_boot_leson.model.Role;
import com.example.spring_boot_leson.model.User;
import com.example.spring_boot_leson.repository.UserRepository;
import com.example.spring_boot_leson.request.LoginRequest;
import com.example.spring_boot_leson.request.UserRegisterRequest;
import com.example.spring_boot_leson.request.UserRequest;
import com.example.spring_boot_leson.response.JWTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    public JWTResponse registerUser(UserRegisterRequest userRegisterRequest) {
        User user = new User(userRegisterRequest.getEmail());
        user.setEmail(userRegisterRequest.getEmail());
        user.setRole(Role.DIRECTOR);
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        if (userRepository.existsByEmail(userRegisterRequest.getEmail()))
            throw new RuntimeException("The email " + userRegisterRequest.getEmail() + " is already in use!");

        User savedUser = userRepository.save(user);
        String token = jwtUtils.generateToken(userRegisterRequest.getEmail());

        return new JWTResponse(
                savedUser.getEmail(),
                token,
                "Dastan",
                savedUser.getRole()

        );
    }


    public void save(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("cant find teacher with this id:" + id));
        User user1 = new User();
        user1.setId(user1.getId());
        user1.setName(user.getName());
        user.setAge(user.getAge());
        return user;
    }

    public void deleteByID(Long id) {
        userRepository.deleteById(id);
    }

    public JWTResponse authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
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





