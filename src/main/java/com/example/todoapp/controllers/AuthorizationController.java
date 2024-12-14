package com.example.todoapp.controllers;

import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
public class AuthorizationController {
    @GetMapping("/secondpage")
    public String secondPage(){
        return "secondpage";
    }

}
