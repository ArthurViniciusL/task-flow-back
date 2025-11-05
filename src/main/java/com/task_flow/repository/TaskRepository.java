package com.task_flow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_flow.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // TaskRepository content will go here
}
