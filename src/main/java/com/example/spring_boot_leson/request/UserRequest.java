package com.example.spring_boot_leson.request;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private String age;
}
