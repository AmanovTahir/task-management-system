package com.company.taskms.dto;

import com.company.taskms.model.enumeration.PriorityType;
import com.company.taskms.model.enumeration.TaskStatus;
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
public class TaskDto {

    @NotBlank
    private String header;
    @NotBlank
    private String description;
    @NotNull
    private TaskStatus status;
    @NotNull
    private PriorityType priority;
    private UserDto author;
    private UserDto assignee;
}
