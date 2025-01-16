package com.example.todoapp.services;

import com.example.todoapp.config.MyUserDetails;
import com.example.todoapp.model.Identification;
import com.example.todoapp.repositories.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class IdentificationDetailService implements UserDetailsService {
    @Autowired
    private IdentificationRepository repository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Identification user = repository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login + " not found");
        }
        return new MyUserDetails(user);
    }
}
