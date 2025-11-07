package com.task_flow.service;

import com.task_flow.dto.UserRegistrationDTO;
import com.task_flow.exception.UsernameAlreadyExistsException;
import com.task_flow.model.User;
import com.task_flow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Cria um novo registro de usuário
    public User registerUser(UserRegistrationDTO registrationDTO) {
        if (userRepository.findByUsername(registrationDTO.username()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }

        User newUser = new User();
        newUser.setUsername(registrationDTO.username());
        newUser.setPassword(passwordEncoder.encode(registrationDTO.password()));
        newUser.setRoles(registrationDTO.roles());

        return userRepository.save(newUser);
    }

}