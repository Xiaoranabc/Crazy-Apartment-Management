package com.example.apartmentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.apartmentManagement.modal.User;
import com.example.apartmentManagement.service.UserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService userService;


    @GetMapping("/saveuser")
    public String saveUser(@RequestParam String role, @RequestParam String name, @RequestParam String email, @RequestParam String password) {
        User user = new User(role, name, email, password);
        userService.saveMyUser(user);
        return "User Saved";
    }
}
