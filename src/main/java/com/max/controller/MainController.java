package com.max.controller;

import com.max.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/")
    public String greeting() {
        return "helloPage";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/main")
    public String main() {

        return "index";
    }


}
