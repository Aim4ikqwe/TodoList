package com.example.todoapp.controllers;

import com.example.todoapp.model.Identification;
import com.example.todoapp.model.TodoItem;
import com.example.todoapp.repositories.IdentificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthorizationController implements CommandLineRunner {

    private final IdentificationRepository identificationRepository;

    public AuthorizationController(IdentificationRepository identificationRepository) {
        this.identificationRepository = identificationRepository;
    }

    @GetMapping
    public String index(Model model){
        return "index";

    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute Identification identification){
        identificationRepository.save(identification);
        return "redirect:/secondpage";
    }
    @PostMapping("/authorization")
    public String authorization(@RequestParam("Login") String Login,@RequestParam("Password") String Password){
        System.out.println(Password);

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

