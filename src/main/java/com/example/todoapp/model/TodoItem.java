package com.example.todoapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class TodoItem {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn( name = "identification_id")
    private Identification identification;
    public TodoItem() {
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}