package com.example.spring_boot_leson.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String Age;
    private String Name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String email) {
    }

    public User() {

    }
}
