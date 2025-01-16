package com.example.todoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.todoapp.model.Identification;

import java.util.Optional;

@Repository
public interface IdentificationRepository extends JpaRepository<Identification, Long> {
        Identification findByLogin(String Login);
        }
