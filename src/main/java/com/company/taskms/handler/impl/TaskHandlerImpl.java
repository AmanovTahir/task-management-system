package com.company.taskms.handler.impl;

import com.company.taskms.dto.TaskDto;
import com.company.taskms.dto.request.TasksRequest;
import com.company.taskms.exception.PermissionDeniedException;
import com.company.taskms.handler.TaskHandler;
import com.company.taskms.mapper.FilterMapper;
import com.company.taskms.mapper.TaskMapper;
import com.company.taskms.model.TaskEntity;
import com.company.taskms.model.UserEntity;
import com.company.taskms.model.enumeration.TaskStatus;
import com.company.taskms.service.PermissionService;
import com.company.taskms.service.TaskService;
import com.company.taskms.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskHandlerImpl implements TaskHandler {

    public static final String EXCEPTION_MESSAGE = "You do not have permission update this task";
    private final TaskService taskService;
    private final UserService userService;
    private final TaskMapper mapper;
    private final PermissionService permissionService;
    private final FilterMapper service;

    @Override
    public TaskDto createTask(TaskDto dto) {
        TaskEntity entity = mapper.toEntity(dto);
        return mapper.toDto(taskService.create(entity));
    }

    @Override
    public TaskDto updateTask(String email, Long taskId, TaskDto dto) {
        TaskEntity task = getById(taskId);
        UserEntity author = userService.getByEmail(email);
        if (permissionService.canManageTask(author, task)) {
            TaskEntity entity = mapper.toEntity(dto);
            entity.setId(taskId);
            return mapper.toDto(taskService.update(entity));
        }
        throw new PermissionDeniedException(EXCEPTION_MESSAGE);
    }

    @Override
    public void deleteTask(String email, Long taskId) {
        TaskEntity task = getById(taskId);
        UserEntity author = userService.getByEmail(email);
        if (permissionService.canManageTask(author, task)) {
            taskService.delete(taskId);
        } else {
            throw new PermissionDeniedException(EXCEPTION_MESSAGE);
        }
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        return mapper.toDto(getById(taskId));
    }

    @Override
    public TaskDto updateTaskStatus(String email, Long taskId, TaskStatus status) {
        TaskEntity task = getById(taskId);
        UserEntity user = userService.getByEmail(email);
        if (permissionService.canManageTask(user, task) || permissionService.canChangeStatus(user, task)) {
            task.setStatus(status);
            return mapper.toDto(taskService.update(task));
        }
        throw new PermissionDeniedException(EXCEPTION_MESSAGE);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks().stream().map(mapper::toDto).toList();
    }

    @Override
    public TaskDto addAssignee(String email, Long taskId, Long userId) {
        TaskEntity task = getById(taskId);
        UserEntity author = userService.getByEmail(email);
        if (permissionService.canManageTask(author, task)) {
            UserEntity assignee = userService.getById(userId);
            task.setAssignee(assignee);
            return mapper.toDto(taskService.update(task));
        }
        throw new PermissionDeniedException(EXCEPTION_MESSAGE);
    }

    @Override
    public List<TaskDto> getTasksByAuthor(TasksRequest request) {
        var pageRequest = PageRequest.of(request.getPage(), request.getSize());
        var specification = service.specification(request, "author");
        return taskService.getTasksByAuthor(specification, pageRequest).stream().map(mapper::toDto).toList();
    }

    @Override
    public List<TaskDto> getTasksByAssignee(TasksRequest request) {
        var pageRequest = PageRequest.of(request.getPage(), request.getSize());
        var specification = service.specification(request, "assignee");
        return taskService.getTasksByAssignee(specification, pageRequest).stream().map(mapper::toDto).toList();
    }

    @Override
    public List<TaskDto> getAllTasksForUser(String email) {
        UserEntity user = userService.getByEmail(email);
        return taskService.getAllTasksByUser(user).stream().map(mapper::toDto).toList();
    }

    private TaskEntity getById(Long taskId) {
        return taskService.getById(taskId);
    }
}
