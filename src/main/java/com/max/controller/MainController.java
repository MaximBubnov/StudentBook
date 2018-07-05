package com.max.controller;

import com.max.domain.User;
import com.max.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/")
    public String greeting() {
        return "helloPage";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "main";
    }


}
