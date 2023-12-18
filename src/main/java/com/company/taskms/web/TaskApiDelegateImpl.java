package com.company.taskms.web;

import com.company.taskms.dto.TaskDto;
import com.company.taskms.dto.request.TasksRequest;
import com.company.taskms.handler.TaskHandler;
import com.company.taskms.model.enumeration.TaskStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Tasks", description = "the Tasks API")
@RequestMapping("/v1/api/tasks/")
public class TaskApiDelegateImpl implements TaskApiDelegate {
    private final TaskHandler handler;

    @Override
    public ResponseEntity<TaskDto> create(TaskDto dto) {
        log.info("Request to create a task: {}", dto);
        return ResponseEntity.ok(handler.createTask(dto));
    }

    @Override
    public ResponseEntity<TaskDto> find(Long taskId) {
        log.info("Request to retrieve a task by ID: {}", taskId);
        return ResponseEntity.ok(handler.getTaskById(taskId));
    }

    @Override
    public ResponseEntity<TaskDto> update(Long taskId, TaskDto dto, Jwt principal) {
        log.info("Request to update a task by ID {}: {}", taskId, dto);
        return ResponseEntity.ok(handler.updateTask(getEmail(principal), taskId, dto));
    }

    @Override
    public ResponseEntity<List<TaskDto>> getAllTasksForUser(Jwt principal) {
        log.info("Request to retrieve all public tasks for the user: {}", getEmail(principal));
        return ResponseEntity.ok(handler.getAllTasksForUser(getEmail(principal)));
    }

    @Override
    public ResponseEntity<Void> delete(Long taskId, Jwt principal) {
        log.info("Request to delete a task by ID: {}", taskId);
        handler.deleteTask(getEmail(principal), taskId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TaskDto> updateStatus(Long taskId, TaskStatus status, Jwt principal) {
        log.info("Request to update task status by ID {}: {}", taskId, status);
        return ResponseEntity.ok(handler.updateTaskStatus(getEmail(principal), taskId, status));
    }

    @Override
    public ResponseEntity<TaskDto> assignTaskToUser(Long taskId, Long userId, Jwt principal) {
        log.info("Request to assign a task to a user. Task ID: {}, User ID: {}", taskId, userId);
        return ResponseEntity.ok(handler.addAssignee(getEmail(principal), taskId, userId));
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTasksByAuthor(TasksRequest request) {
        log.info("Request to retrieve tasks by author ID: {}", request);
        List<TaskDto> tasks = handler.getTasksByAuthor(request);
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTasksByAssignee(TasksRequest request) {
        log.info("Request to retrieve tasks by assignee ID: {}", request);
        List<TaskDto> tasks = handler.getTasksByAssignee(request);
        return ResponseEntity.ok(tasks);
    }

    private String getEmail(Jwt principal) {
        return principal.getClaim("email");
    }
}

