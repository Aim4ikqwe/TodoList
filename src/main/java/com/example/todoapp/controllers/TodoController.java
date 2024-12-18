package com.example.todoapp.controllers;

import com.example.todoapp.TodoApplication;
import com.example.todoapp.model.TodoItem;
import com.example.todoapp.repositories.TodoItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TodoController implements CommandLineRunner {

    private final TodoItemRepository todoItemRepository;

    public TodoController(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping("/secondpage")
    public String secondpage(Model model){

        List<TodoItem> allTodos = todoItemRepository.findAll();
        model.addAttribute("allTodos", allTodos);
        model.addAttribute("newTodo", new TodoItem());
        return "secondpage";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute TodoItem todoItem) {
        todoItemRepository.save(todoItem);

        return "redirect:/secondpage";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        todoItemRepository.deleteById(id);
        return "redirect:/secondpage";
    }
    @PostMapping("/deleteAll")
    public String deleteAll(){
        todoItemRepository.deleteAll();
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
