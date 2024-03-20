package com.example.spring_boot_leson.api;

import com.example.spring_boot_leson.model.Company1;
import com.example.spring_boot_leson.request.Company1Request;
import com.example.spring_boot_leson.service.Company1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('DIRECTOR','RIGHTEMPLOYES')")
public class Company1Api {
    private final Company1Service service;


    @PostMapping("/save")
    public String saveCompany1(@RequestBody Company1Request request) {
        service.save(request);
        return "Saved";
    }

    @GetMapping("/all")
    public List<Company1> getAll() {
        return service.findAll();
    }


    @GetMapping("/get/by/{id}")
    public Company1 getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete/by/{id}")
    public String deleteById(@PathVariable Long id) {
        service.deleteByID(id);
        return "deleted 77";
    }

    @PostMapping("register")
    public String reg() {
        return "registred";
    }
}
