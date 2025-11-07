package com.task_flow.task_flow_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task_flow.controller.AuthController;
import com.task_flow.dto.LoginRequestDTO;
import com.task_flow.dto.UserRegistrationDTO;
import com.task_flow.service.AuthService;
import com.task_flow.util.JwtUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Testes do AuthController (MockMvc + Mockito)")
class AuthControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    // dependências mockadas
    @Mock private AuthenticationManager authenticationManager;
    @Mock private UserDetailsService userDetailsService;
    @Mock private JwtUtil jwtUtil;
    @Mock private AuthService authService;

    // controller real sendo testado
    @InjectMocks private AuthController authController;

    private UserRegistrationDTO registrationDTO;
    private LoginRequestDTO loginDTO;
    private UserDetails mockUser;

    @BeforeEach
    @DisplayName("Configuração inicial antes de cada teste")
    void setup() {
        MockitoAnnotations.openMocks(this);

        // monta MockMvc standalone
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        registrationDTO = new UserRegistrationDTO(
                "testuser",
                "password123",
                Collections.emptySet()
        );

        loginDTO = new LoginRequestDTO("testuser", "password123");

        mockUser = new User("testuser", "password123", Collections.emptyList());
    }

    @Test
    @DisplayName("Deve registrar um usuário com sucesso")
    void registerUser_success() throws Exception {
        doNothing().when(authService).registerUser(any());

        mockMvc.perform(
                        post("/api/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(registrationDTO))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully!"));

        verify(authService).registerUser(any());
    }

    @Test
    @DisplayName("Deve autenticar usuário válido e retornar JWT")
    void loginUser_success() throws Exception {
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(mockUser);
        when(jwtUtil.generateToken(mockUser)).thenReturn("mocked_token");

        mockMvc.perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(loginDTO))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").value("mocked_token"))
                .andExpect(jsonPath("$.username").value("testuser"));

        verify(authenticationManager).authenticate(any());
        verify(userDetailsService).loadUserByUsername("testuser");
        verify(jwtUtil).generateToken(mockUser);
    }

    @Test
    @DisplayName("Deve retornar 401 quando as credenciais forem inválidas")
    void loginUser_invalidCredentials() throws Exception {
        doThrow(new BadCredentialsException("Invalid"))
                .when(authenticationManager)
                .authenticate(any());

        mockMvc.perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(loginDTO))
                )
                .andExpect(status().isUnauthorized());

        verify(authenticationManager).authenticate(any());
        verify(userDetailsService, never()).loadUserByUsername(anyString());
        verify(jwtUtil, never()).generateToken(any());
    }
}
