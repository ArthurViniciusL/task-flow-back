package com.task_flow.controller;

import com.task_flow.dto.LoginRequestDTO;
import com.task_flow.dto.LoginResponseDTO;
import com.task_flow.dto.UserRegistrationDTO;
import com.task_flow.service.AuthService;
import com.task_flow.util.JwtUtil;
import com.task_flow.annotation.ApiResponseDocumentation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints de autenticação e registro de usuários")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @Operation(summary = "Registrar novo usuário", description = "Cria uma nova conta de usuário no sistema")
    @ApiResponseRegister
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
        authService.registerUser(registrationDTO);
        return ResponseEntity.ok("User registered successfully!");
    }

    @Operation(summary = "Fazer login", description = "Autentica um usuário e retorna o token JWT")
    @ApiResponseLogin
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponseDTO(jwt, userDetails.getUsername()));
    }
}