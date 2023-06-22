package org.coursework.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.model.User;
import org.coursework.project_warehouse.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository repository;

    public User getPersona() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByUsername(username);
    }

    public String createPersona(String username, String password) {
        List<User> allUser = repository.findAll();
        for (User user : allUser) {
            if (user.getUsername().equals(username))
                return "";
        }

        User user = User.builder()
                .auth("ROLE_USER")
                .password(passwordEncoder.encode(password))
                .username(username)
                .cart(new ArrayList<>())
                .build();
        repository.save(user);
        return "";

    }
}
