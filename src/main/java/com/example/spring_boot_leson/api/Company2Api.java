package com.example.spring_boot_leson.api;

import com.example.spring_boot_leson.model.Company2;
import com.example.spring_boot_leson.request.Company2Request;
import com.example.spring_boot_leson.service.Company2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@PreAuthorize("hasAnyAuthority('DIRECTOR','LEFTEMPLOYEES')")
@RestController
@RequiredArgsConstructor
public class Company2Api {
    private final Company2Service service;


    @PostMapping
    public String saveCompany2(@RequestBody Company2Request request){
        service.save(request);
        return "Saved";
    }
    @GetMapping("/alll")
    public List<Company2> getAll(){return service.findAll();}


    @GetMapping("/get/byy/{id}")
    public Company2 getById(@PathVariable Long id){return service.getById(id);}

    @DeleteMapping("/delete/byy/{id}")
    public  String deleteById(@PathVariable Long id){
        service.deleteByID(id);
        return "deleted 77";
    }

}




