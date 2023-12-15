package com.company.taskms.service.impl;

import com.company.taskms.exception.TaskNotFoundException;
import com.company.taskms.model.TaskEntity;
import com.company.taskms.model.UserEntity;
import com.company.taskms.repository.TaskRepository;
import com.company.taskms.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskEntity create(TaskEntity task) {
        log.info("Creating a new task: {}", task);
        TaskEntity createdTask = taskRepository.save(task);
        log.info("Task created successfully with id: {}", createdTask.getId());
        return createdTask;
    }

    @Override
    public TaskEntity update(TaskEntity updatedTask) {
        log.info("Updating task with id {}: {}", updatedTask.getId(), updatedTask);
        TaskEntity updatedEntity = taskRepository.save(updatedTask);
        log.info("Task updated successfully with id: {}", updatedEntity.getId());
        return updatedEntity;
    }

    @Override
    public void delete(Long taskId) {
        log.info("Deleting task with id: {}", taskId);
        taskRepository.deleteById(taskId);
        log.info("Task deleted successfully with id: {}", taskId);
    }

    @Override
    public TaskEntity getById(Long taskId) {
        log.info("Getting task by id: {}", taskId);
        Optional<TaskEntity> taskOptional = taskRepository.findById(taskId);
        TaskEntity task = taskOptional.orElseThrow(() ->
                new TaskNotFoundException("Task not found with id: " + taskId));
        log.info("Task retrieved successfully: {}", task);
        return task;
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        log.info("Getting all tasks");
        List<TaskEntity> tasks = taskRepository.findAll();
        log.info("Retrieved {} tasks", tasks.size());
        return tasks;
    }

    @Override
    public List<TaskEntity> getTasksByAuthor(Specification<TaskEntity> specification, PageRequest pageRequest) {
        log.info("Getting tasks by author with specification: {}, pageRequest: {}", specification, pageRequest);
        List<TaskEntity> tasks = taskRepository.findAll(specification, pageRequest).getContent();
        log.info("Retrieved {} tasks by author", tasks.size());
        return tasks;
    }

    @Override
    public List<TaskEntity> getTasksByAssignee(Specification<TaskEntity> specification, PageRequest pageRequest) {
        log.info("Getting tasks by assignee with specification: {}, pageRequest: {}", specification, pageRequest);
        List<TaskEntity> tasks = taskRepository.findAll(specification, pageRequest).getContent();
        log.info("Retrieved {} tasks by assignee", tasks.size());
        return tasks;
    }

    @Override
    public List<TaskEntity> getAllTasksByUser(UserEntity user) {
        log.info("Getting all tasks by user: {}", user);
        List<TaskEntity> tasks = taskRepository.findAllByAuthor(user);
        log.info("Retrieved {} tasks by user", tasks.size());
        return tasks;
    }
}
