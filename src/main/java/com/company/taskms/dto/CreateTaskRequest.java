package com.company.taskms.dto;


import lombok.Data;

@Data
public class CreateTaskRequest {
    private TaskDto task;
    private Long authorId;
    private Long assigneeId;
}
