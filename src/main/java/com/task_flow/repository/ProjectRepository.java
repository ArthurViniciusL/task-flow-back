package com.task_flow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_flow.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // ProjectRepository content will go here
}
