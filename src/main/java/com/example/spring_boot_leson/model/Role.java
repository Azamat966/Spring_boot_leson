package com.example.spring_boot_leson.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    DIRECTOR,RIGHTEMPLOYES,LEFTEMPLOYEES;

    @Override
    public String getAuthority() {
        return name();
    }
}

