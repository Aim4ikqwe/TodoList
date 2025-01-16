package com.example.todoapp.repositories;

import com.example.todoapp.model.Identification;
import com.example.todoapp.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByIdentificationId(Long IdentificationId);
    void removeByIdentificationId(Long IdentificationId);
}
