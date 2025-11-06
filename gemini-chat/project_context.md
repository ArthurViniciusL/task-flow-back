# Project Context: task-flow-back

## Overview
This document summarizes the current state and structure of the `task-flow-back` project, a Spring Boot application designed for task management. It outlines the project's core technologies, dependencies, configuration, and existing Java packages and classes.

## Project Details
*   **Project Name:** `task-flow-app`
*   **Group ID:** `com.task-flow`
*   **Version:** `0.0.1-SNAPSHOT`
*   **Java Version:** 21
*   **Spring Boot Version:** 3.5.7

## Dependencies (from `pom.xml`)
The project utilizes the following key dependencies:
*   `spring-boot-starter-data-jpa`: For JPA persistence with Hibernate.
*   `spring-boot-starter-security`: For robust security features provided by Spring Security.
*   `spring-boot-starter-web`: Essential for building RESTful web services.
*   `spring-boot-devtools`: Enhances development experience with features like automatic restarts.
*   `h2database`: An in-memory database, often used for development and testing.
*   `mysql-connector-j`: JDBC driver for connecting to MySQL databases.
*   `lombok`: A library to reduce boilerplate code in Java classes (e.g., getters, setters, constructors).
*   `spring-boot-starter-test`: Comprehensive utilities for testing Spring Boot applications.
*   `spring-security-test`: Specific testing support for Spring Security.
*   `springdoc-openapi-starter-webmvc-ui`: Integrates Swagger UI for automatic API documentation.
*   `jjwt-api`, `jjwt-impl`, `jjwt-jackson`: Libraries for implementing JSON Web Tokens (JWT) for secure authentication.

## Configuration (from `src/main/resources/application.yaml`)
The `application.yaml` file contains the following configurations:
*   `jwt.secret`: A placeholder for the JWT secret key, crucial for token signing and verification.
*   `jwt.expiration`: Defines the expiration time for JWT tokens (currently 1 hour).

## Existing Java Packages and Classes

The project follows a well-organized package structure under `com.task_flow`, indicating a clear separation of concerns:

### `com.task_flow.config`
*   `JwtRequestFilter.java`: A custom filter responsible for intercepting requests, validating JWT tokens, and setting the authentication context.
*   `SecurityConfig.java`: Configures Spring Security, defining authentication providers, password encoders, and authorization rules for various endpoints.

### `com.task_flow.controller`
This package contains REST controllers that expose API endpoints for different functionalities:
*   `AuthController.java`: Manages user authentication processes, including registration and login.
*   `CommentController.java`: Handles operations related to task comments.
*   `DashboardController.java`: Provides endpoints for dashboard-related data and analytics.
*   `NotificationController.java`: Manages user notifications.
*   `ProjectController.java`: Handles CRUD operations for projects.
*   `TaskController.java`: Manages CRUD operations for tasks.
*   `UserController.java`: Provides endpoints for user management (e.g., retrieving user profiles).

### `com.task_flow.dto`
This package holds Data Transfer Objects (DTOs) used for data exchange between the client and the server. All DTOs are based on Java Records, ensuring immutability:
*   `CommentRequestDTO.java` (Record)
*   `CommentResponseDTO.java` (Record)
*   `DashboardResponseDTO.java` (Record)
*   `LoginRequestDTO.java` (Record)
*   `LoginResponseDTO.java` (Record)
*   `NotificationResponseDTO.java` (Record)
*   `ProjectRequestDTO.java` (Record)
*   `ProjectResponseDTO.java` (Record)
*   `TaskRequestDTO.java` (Record)
*   `TaskResponseDTO.java` (Record)
*   `UserRegistrationDTO.java` (Record)
*   `UserResponseDTO.java` (Record)

### `com.task_flow.exception`
*   `GlobalExceptionHandler.java`: A centralized exception handler to provide consistent error responses across the application.

### `com.task_flow.model`
This package defines the core domain entities and enums:
*   `Comment.java`: Represents comments associated with tasks.
*   `Notification.java`: Represents notifications sent to users.
*   `Priority.java`: An enum defining task priorities (e.g., LOW, MEDIUM, HIGH).
*   `Project.java`: Represents a project, which can contain multiple tasks.
*   `Role.java`: An enum defining user roles (e.g., ADMIN, MANAGER, COLLABORATOR).
*   `Status.java`: An enum defining task statuses (e.g., TODO, IN_PROGRESS, DONE).
*   `Task.java`: Represents a single task with attributes like title, description, due date, assignee, and status.
*   `User.java`: Represents a user with attributes like username, password, and roles.

### `com.task_flow.repository`
This package contains Spring Data JPA repositories for data access:
*   `CommentRepository.java`
*   `NotificationRepository.java`
*   `ProjectRepository.java`
*   `TaskRepository.java`
*   `UserRepository.java`

### `com.task_flow.service`
This package contains service classes that encapsulate business logic:
*   `AuthService.java`: Handles user authentication and registration logic.
*   `CommentService.java`
*   `NotificationService.java`
*   `ProjectService.java`
*   `TaskService.java`
*   `UserService.java`

### `com.task_flow.util`
*   `JwtUtil.java`: Provides utility methods for generating, validating, and parsing JWT tokens.

### `com.task_flow`
*   `TaskFlowAppApplication.java`: The main entry point for the Spring Boot application.

## Existing Test Files
*   `com.task_flow.task_flow_app.TaskFlowAppApplicationTests.java`: Contains basic integration tests for the Spring Boot application context.

## Overall Functionality
The `task-flow-back` project is a robust backend for a task management application. It features a well-structured codebase with clear separation of concerns, leveraging Spring Boot's capabilities for RESTful APIs, data persistence, and security. The integration of JWT for authentication, Spring Security for authorization, and Swagger for API documentation indicates a modern and maintainable architecture. The existing entities and services cover core functionalities like user management, project management, task management, comments, and notifications.
