package com.example.spring_boot_leson.api;




import com.example.spring_boot_leson.model.Role;
import com.example.spring_boot_leson.model.User;
import com.example.spring_boot_leson.request.LoginRequest;
import com.example.spring_boot_leson.request.UserRegisterRequest;
import com.example.spring_boot_leson.request.UserRequest;
import com.example.spring_boot_leson.response.JWTResponse;
import com.example.spring_boot_leson.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;
@RestController
@RequiredArgsConstructor

public class UserApi {
    private final UserService service;


    @PostMapping("register7")
    @PermitAll
    public JWTResponse registrationPerson(@RequestBody UserRegisterRequest userRegisterRequest) {
        return service.registerUser( userRegisterRequest );
    }

    @PostMapping("/saved")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public String User(@RequestBody UserRequest request, @RequestParam Role role){
        service.save(request);
        return "Saved";
    }
    @GetMapping("/all1")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public List<User> getAll(){return service.findAll();}


    @GetMapping("/get/byt/{id}")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public User getById(@PathVariable Long id){return service.getById(id);}

    @DeleteMapping("/deleted/by/{id}")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public  String deleteById(@PathVariable Long id){
        service.deleteByID(id);
        return "deleted 77";
    }
    @PostMapping("login")
    @PermitAll
    public JWTResponse performLogin(@RequestBody LoginRequest loginResponse) {
        return service.authenticate( loginResponse );
    }

}
