package com.company.taskms.dto.request;

import com.company.taskms.model.enumeration.PriorityType;
import com.company.taskms.model.enumeration.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(name = "FilterRequest", description = "Filter criteria for tasks")
public class FilterRequest {
    @Schema(
            name = "header",
            description = "Task header",
            defaultValue = "Task 1"
    )
    private String header;

    @Schema(
            name = "description",
            description = "Task description",
            defaultValue = "Description for Task 1"
    )
    private String description;

    @Schema(
            name = "status",
            description = "Task status",
            defaultValue = "IN_PROGRESS"
    )
    private TaskStatus status;

    @Schema(
            name = "priority",
            description = "Task priority",
            defaultValue = "MEDIUM"
    )
    private PriorityType priority;

    @Schema(
            name = "authorEmail",
            description = "Email of the task author",
            defaultValue = "user1@example.com"
    )
    private String authorEmail;

    @Schema(
            name = "assigneeEmail",
            description = "Email of the task assignee",
            defaultValue = "user1@example.com"
    )
    private String assigneeEmail;
}
