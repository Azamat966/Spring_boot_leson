package com.example.spring_boot_leson.service;
import com.example.spring_boot_leson.model.Company1;
import com.example.spring_boot_leson.model.Role;
import com.example.spring_boot_leson.repository.Company1Repository;

import com.example.spring_boot_leson.request.Company1Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Company1Service {
    private  final Company1Repository company1Repository;


    public void save(Company1Request request) {
        Company1 company1 = new Company1();
        company1.setName(request.getName());
        company1.setAge(request.getAge());
        company1Repository.save(company1);
    }

    public List<Company1> findAll() {
        return company1Repository.findAll();
    }


    public Company1 getById(Long id) {
         Company1 company12 = company1Repository.findById(id).orElseThrow(() -> new RuntimeException("cant find teacher with this id:" + id));
        Company1 company1 = new Company1();
        company1.setId(company1.getId());
        company1.setName(company12.getName());
        company1.setAge(company12.getAge());
        return company1;
    }
    public void deleteByID(Long id){company1Repository.deleteById(id);}

}

