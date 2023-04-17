package com.example.apartmentManagement.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.apartmentManagement.modal.User;
import com.example.apartmentManagement.service.UserService;
import org.springframework.web.context.request.RequestAttributes;

@Controller
public class ApplicationController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String showLoginPage() {
        return "welcome";
    }

    @RequestMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @RequestMapping("/staffLog")
    public String showStaffPage() {
        return "staffLog";
    }

    @RequestMapping("/residentLog")
    public String showResidentPage() {
        return "residentLog";
    }

    @RequestMapping("/applicantLog")
    public String showApplicantPage() {
        return "applicantLog";
    }

    @RequestMapping ("/login-user")
    public String loginUser(@ModelAttribute User user, HttpServletRequest request, HttpSession session) {
        User thisuser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (thisuser != null) {
            if("staff".equals(thisuser.getRole())) {
                session.setAttribute("name", thisuser.getName());
                return "redirect:/staffLog";
            } else if("resident".equals(thisuser.getRole())) {
                session.setAttribute("name", thisuser.getName());
                Integer id = thisuser.getId();
                session.setAttribute("id", id);
               return "redirect:/residentLog";
            } else {
                session.setAttribute("name", thisuser.getName());
                Integer id = thisuser.getId();
                session.setAttribute("id", id);
                return "redirect:/applicantLog";
            }
        } else {
            request.setAttribute("mode", "Invalid");
            return "welcome";
        }
    }

    @PostMapping("/save-user")
    public String registerUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
        userService.saveMyUser(user);
        return "welcome";
    }

    @RequestMapping("/log-out")
    public String LogOut(HttpServletRequest request) {
        return "welcome";
    }




    }
