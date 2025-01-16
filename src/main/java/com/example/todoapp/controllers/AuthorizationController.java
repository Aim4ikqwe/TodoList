package com.example.todoapp.controllers;

import com.example.todoapp.model.Identification;
import com.example.todoapp.model.TodoItem;
import com.example.todoapp.repositories.IdentificationRepository;
import com.example.todoapp.services.IdentificationDetailService;
import jakarta.persistence.Id;
import lombok.extern.java.Log;
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

    @PostMapping("/registration")
    public String registration(@RequestParam("Login") String Login, @RequestParam("Password") String Password, Model model) {
        // Проверяем, существует ли пользователь с данным логином
        Identification existingUser = identificationRepository.findByLogin(Login);
        if (existingUser != null) {
            // Добавляем сообщение об ошибке в модель
            model.addAttribute("error", "Пользователь с таким логином уже существует");
            return "register"; // Возвращаем страницу регистрации
        }

        // Создаем нового пользователя
        Identification newUser = new Identification();
        newUser.setLogin(Login);
        newUser.setPassword(passwordEncoder.encode(Password));
        newUser.setRoles("ROLE_USER");
        identificationRepository.save(newUser);

        return "redirect:/"; // Перенаправляем на главную страницу
    }
    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

