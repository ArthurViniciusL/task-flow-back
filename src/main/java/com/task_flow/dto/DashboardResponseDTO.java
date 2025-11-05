package com.task_flow.dto;

import com.task_flow.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDTO {
    private Map<Status, Long> taskCountsByStatus;
    // Other dashboard metrics can be added here later
}
