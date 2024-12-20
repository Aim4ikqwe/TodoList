package com.example.todoapp.controllers;

import com.example.todoapp.model.Identification;
import com.example.todoapp.model.TodoItem;
import com.example.todoapp.repositories.IdentificationRepository;
import com.example.todoapp.services.IdentificationDetailService;
import jakarta.persistence.Id;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorizationController implements CommandLineRunner {
   private final IdentificationRepository identificationRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthorizationController(IdentificationRepository identificationRepository, PasswordEncoder passwordEncoder) {
        this.identificationRepository = identificationRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping
    public String index(Model model){
        model.addAttribute("identification", new Identification());
        return "index";

    }

    @PostMapping("/registration")
    public String registration(@RequestParam("Login") String Login, @RequestParam("Password") String Password){
        Identification newUser = new Identification();
        newUser.setLogin(Login);
        newUser.setPassword(passwordEncoder.encode(Password));
        newUser.setRoles("ROLE_USER");
        identificationRepository.save(newUser);

        return "index";
    }
    @PostMapping("/authorization")
    public String authorization(@RequestParam("Login") String Login,@RequestParam("Password") String Password){


        return "index";
    }
    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

