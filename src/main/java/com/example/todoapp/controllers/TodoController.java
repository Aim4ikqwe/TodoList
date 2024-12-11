package com.example.todoapp.controllers;

import com.example.todoapp.model.TodoItem;
import com.example.todoapp.repositories.TodoItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TodoController implements CommandLineRunner {

    private final TodoItemRepository todoItemRepository;

    public TodoController(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping
    public String index(Model model){

        List<TodoItem> allTodos = todoItemRepository.findAll();
        model.addAttribute("allTodos", allTodos);
        model.addAttribute("newTodo", new TodoItem());


        return "index";
    }

    @PostMapping("/add")
public String add(@ModelAttribute TodoItem todoItem){
        todoItemRepository.save(todoItem);

        return "redirect:/";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        todoItemRepository.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/deleteAll")
    public String deleteAll(){
        todoItemRepository.deleteAll();
        return "redirect:/";
    }
    @Override
    public void run(String... args) throws Exception {

    }
}
