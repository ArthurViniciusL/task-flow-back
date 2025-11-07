package com.task_flow.repository;

import com.task_flow.model.Task;
import com.task_flow.model.Status;
import com.task_flow.model.Priority;
import com.task_flow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);
    List<Task> findByAssignee(User assignee);
    List<Task> findByPriority(Priority priority);
    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleKeyword, String descriptionKeyword);
    List<Task> findByProjectId(Long projectId);

    long countByStatus(Status status);
}