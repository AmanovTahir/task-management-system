package com.company.taskms.service.impl;

import com.company.taskms.model.TaskEntity;
import com.company.taskms.model.UserEntity;
import com.company.taskms.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Override
    public boolean canManageTask(UserEntity user, TaskEntity task) {
        UserEntity authorTasks = task.getAuthor();
        if (authorTasks == null) {
            log.warn("Task author is null. Unable to check if the user can manage the task.");
            return false;
        }
        boolean canManage = authorTasks.equals(user);
        log.debug("User {} {} manage task with id {}: {}", user.getId(),
                canManage ? "can" : "cannot", task.getId(), task);
        return canManage;
    }

    @Override
    public boolean canChangeStatus(UserEntity user, TaskEntity task) {
        UserEntity assignee = task.getAssignee();
        if (assignee == null) {
            log.warn("Task assignee is null. Unable to check if the user can change the status.");
            return false;
        }
        boolean canChangeStatus = assignee.equals(user);
        log.debug("User {} {} change status for task with id {}: {}", user.getId(),
                canChangeStatus ? "can" : "cannot", task.getId(), task);
        return canChangeStatus;
    }
}
