package com.company.taskms.dto.request;

import com.company.taskms.model.enumeration.PriorityType;
import com.company.taskms.model.enumeration.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FilterRequest {
    private String header;
    private String description;
    private TaskStatus status;
    private PriorityType priority;
    private String authorEmail;
    private String assigneeEmail;
}
