package com.task_flow.service;

import com.task_flow.dto.UserRegistrationDTO;
import com.task_flow.model.User;
import com.task_flow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDTO registrationDTO) {
        if (userRepository.findByUsername(registrationDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!"); // Or a more specific exception
        }

        User newUser = new User();
        newUser.setUsername(registrationDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        newUser.setRoles(registrationDTO.getRoles()); // Set roles from DTO

        return userRepository.save(newUser);
    }

    // Authentication logic will be added here later
}