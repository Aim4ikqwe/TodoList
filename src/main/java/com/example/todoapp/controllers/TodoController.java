package com.example.todoapp.controllers;

import com.example.todoapp.TodoApplication;
import com.example.todoapp.model.Identification;
import com.example.todoapp.model.TodoItem;
import com.example.todoapp.repositories.IdentificationRepository;
import com.example.todoapp.repositories.TodoItemRepository;
import jakarta.persistence.Id;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class TodoController implements CommandLineRunner {

    private final TodoItemRepository todoItemRepository;
    private final IdentificationRepository identificationRepository;

    public TodoController(TodoItemRepository todoItemRepository, IdentificationRepository identificationRepository) {
        this.todoItemRepository = todoItemRepository;
        this.identificationRepository = identificationRepository;
    }

    @GetMapping("/secondpage")
    public String secondpage(Model model, @AuthenticationPrincipal UserDetails currentUser){ // Для получения пользователя
        Identification identification = identificationRepository.findByLogin(currentUser.getUsername());
        List<TodoItem> allTodos = todoItemRepository.findByIdentificationId(identification.getId());
        model.addAttribute("allTodos", allTodos);
        model.addAttribute("newTodo", new TodoItem());
        return "secondpage";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute TodoItem todoItem, @AuthenticationPrincipal UserDetails currentUser) {
         Identification user = identificationRepository.findByLogin(currentUser.getUsername());
         todoItem.setIdentification(user);
        todoItemRepository.save(todoItem);

        return "redirect:/secondpage";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        todoItemRepository.deleteById(id);
        return "redirect:/secondpage";
    }
    @PostMapping("/deleteAll")
    public String deleteAll(@AuthenticationPrincipal UserDetails currentUser){
        Identification user = identificationRepository.findByLogin(currentUser.getUsername());
        todoItemRepository.deleteByIdentificationId(user.getId()); //TODO Доделать, чтобы удалял только свои задачи, а не все!
        return "redirect:/secondpage";
    }
    @PostMapping("/search")
    public String searching(@RequestParam("searchTerm") String searchTerm, Model model) {
        List <TodoItem> allItems = todoItemRepository.findAll();
        List <TodoItem> searchResults = allItems.stream().filter(item -> item.getTitle().toLowerCase().contains(searchTerm.toLowerCase())).collect(Collectors.toList());

        model.addAttribute("allTodos", searchResults);
        model.addAttribute("newTodo", new TodoItem());
        return "secondpage";
    }
    @Override
    public void run(String... args) throws Exception {

    }
}
