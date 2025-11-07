package com.task_flow.controller;

import com.task_flow.dto.UserRegistrationDTO;
import com.task_flow.dto.UserResponseDTO;
import com.task_flow.service.UserService;
import com.task_flow.annotation.ApiResponseDocumentation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "API de gerenciamento de usuários")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Listar todos os usuários", description = "Retorna a lista completa de usuários cadastrados no sistema (apenas ADMIN)")
    @ApiResponseGetList
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can view all users
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo seu identificador (ADMIN ou o próprio usuário)")
    @ApiResponseGetById
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id") // ADMIN or the user themselves
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente (apenas ADMIN)")
    @ApiResponsePut
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can update users
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRegistrationDTO userDetails) {
        UserResponseDTO updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Excluir usuário", description = "Remove um usuário do sistema (apenas ADMIN)")
    @ApiResponseDelete
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can delete users
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}