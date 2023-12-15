package com.company.taskms.mapper;

import com.company.taskms.dto.TaskDto;
import com.company.taskms.dto.UserDto;
import com.company.taskms.model.TaskEntity;
import com.company.taskms.model.UserEntity;
import com.company.taskms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper extends AbstractMapper<TaskDto, TaskEntity> {

    private final UserMapper mapper;
    private final UserService userService;

    @Override
    public TaskDto toDto(TaskEntity taskEntity) {
        UserDto author = taskEntity.getAuthor() == null ? null : mapper.toDto(taskEntity.getAuthor());
        UserDto assignee = taskEntity.getAssignee() == null ? null : mapper.toDto(taskEntity.getAssignee());

        return TaskDto.builder()
                .assignee(assignee)
                .author(author)
                .header(taskEntity.getHeader())
                .status(taskEntity.getStatus())
                .priority(taskEntity.getPriority())
                .description(taskEntity.getDescription())
                .build();
    }

    @Override
    public TaskEntity toEntity(TaskDto taskDto) {
        UserEntity author = taskDto.getAuthor() == null
                ? null
                : userService.getByEmail(taskDto.getAuthor().getEmail());
        UserEntity assignee = taskDto.getAssignee() == null
                ? null
                : userService.getByEmail(taskDto.getAssignee().getEmail());
        return TaskEntity.builder()
                .assignee(assignee)
                .author(author)
                .header(taskDto.getHeader())
                .status(taskDto.getStatus())
                .priority(taskDto.getPriority())
                .description(taskDto.getDescription())
                .build();
    }
}
