package com.company.taskms.dto;

import com.company.taskms.model.enumeration.PriorityType;
import com.company.taskms.model.enumeration.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "TaskDto", description = "Representation of a task")
public class TaskDto {

    @NotBlank(message = "Header cannot be blank")
    @Schema(description = "Task header", defaultValue = "simple Task")
    private String header;

    @NotBlank(message = "Description cannot be blank")
    @Schema(description = "Task description", defaultValue = "simple description of task")
    private String description;

    @NotNull(message = "Status cannot be null")
    @Schema(description = "Task status", defaultValue = "IN_PROGRESS")
    private TaskStatus status;

    @NotNull(message = "Priority cannot be null")
    @Schema(description = "Task priority", defaultValue = "LOW")
    private PriorityType priority;

    @Schema(description = "Author of the task")
    private UserDto author;

    @Schema(description = "Assignee of the task")
    private UserDto assignee;
}