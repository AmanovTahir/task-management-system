package com.company.taskms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TasksRequest {
    private long userId;
    private int page;
    private int size;
    private FilterRequest filterRequest;
}
