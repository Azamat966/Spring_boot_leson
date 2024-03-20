package com.example.spring_boot_leson.response;

import com.example.spring_boot_leson.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class JWTResponse {

        private String email;
        private String token;
        private String message;
        private Role role;
    }

