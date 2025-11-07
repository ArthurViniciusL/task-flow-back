package com.task_flow.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Task Flow API",
                version = "1.0.0",
                description = "API REST para gerenciamento de tarefas, projetos e equipes",

                contact = @Contact(
                        name = "Task Flow Team",
                        email = "contato@taskflow.com",
                        url = "https://github.com/ArthurViniciusL/task-flow-back"
                ),

                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        ),

        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Ambiente de Desenvolvimento Local"
                ),
                @Server(
                        url = "https://api.taskflow.com",
                        description = "Ambiente de Produção"
                )
        },

        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Autenticação JWT - Insira o token no formato: Bearer {token}",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {


}