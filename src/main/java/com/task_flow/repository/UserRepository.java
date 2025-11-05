package com.task_flow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_flow.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // UserRepository content will go here
}
