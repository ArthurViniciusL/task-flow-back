package com.task_flow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDTO {
    private String name;
    private String description;
    private Set<Long> teamMemberIds; // IDs of users involved in the project
}