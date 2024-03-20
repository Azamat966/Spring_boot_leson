package com.example.spring_boot_leson.service;

import com.example.spring_boot_leson.model.Company1;
import com.example.spring_boot_leson.model.Company2;
import com.example.spring_boot_leson.model.Role;
import com.example.spring_boot_leson.repository.Company2Repository;
import com.example.spring_boot_leson.request.Company1Request;
import com.example.spring_boot_leson.request.Company2Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class Company2Service {
    private final Company2Repository company2Repository;

    public void save(Company2Request request) {
        Company2 company2 = new Company2();
        company2.setName(request.getName());
        company2.setAge(request.getAge());
        company2Repository.save(company2);
    }

    public List<Company2> findAll() {
        return company2Repository.findAll();
    }


    public Company2 getById(Long id) {
        Company2 company22 = company2Repository.findById(id).orElseThrow(() -> new RuntimeException("cant find teacher with this id:" + id));
        Company2 company2 = new Company2();
        company2.setId(company2.getId());
        company2.setName(company22.getName());
        company2.setAge(company22.getAge());
        return company2;
    }
    public void deleteByID(Long id){company2Repository.deleteById(id);}

}



