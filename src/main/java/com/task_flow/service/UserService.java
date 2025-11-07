package com.task_flow.service;

import com.task_flow.dto.UserRegistrationDTO;
import com.task_flow.dto.UserResponseDTO;
import com.task_flow.exception.UserNotFoundException;
import com.task_flow.model.User;
import com.task_flow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Metodo que cria um novo registro de usuário
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    //Metodo que lista todos os usuarios
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToUserResponseDTO)
                .collect(Collectors.toList());
    }

    //Metodo que lista um usuario pelo ID
    public UserResponseDTO getUserById(Long id) {
        User user = findUserById(id);
        return convertToUserResponseDTO(user);
    }

    //Metodo que atualiza um usuario
    public UserResponseDTO updateUser(Long id, UserRegistrationDTO userDetails) {
        User existingUser = findUserById(id);

        existingUser.setUsername(userDetails.username());
        // Password update should be handled separately or with more security checks
        if (userDetails.password() != null && !userDetails.password().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDetails.password()));
        }
        existingUser.setRoles(userDetails.roles());

        User updatedUser = userRepository.save(existingUser);
        return convertToUserResponseDTO(updatedUser);
    }

    //Metodo que deleta um usuario
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    //Metodo que lista um usuario pelo ID
    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    //Metodo que converte um usuario em DTO
    public UserResponseDTO convertToUserResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getRoles());
    }
}