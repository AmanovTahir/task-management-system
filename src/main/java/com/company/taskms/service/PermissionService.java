package com.company.taskms.service;

import com.company.taskms.model.TaskEntity;
import com.company.taskms.model.UserEntity;

/**
 * Интерфейс PermissionService предоставляет методы для проверки различных разрешений, связанных с задачами.
 */
public interface PermissionService {

    /**
     * Проверяет, может ли пользователь управлять задачей.
     *
     * @param user пользователь, для которого проверяется разрешение
     * @param task задача, для которой проверяется разрешение
     * @return {@code true}, если пользователь может управлять задачей, в противном случае {@code false}
     */
    boolean canManageTask(UserEntity user, TaskEntity task);

    /**
     * Проверяет, может ли пользователь изменить статус задачи.
     *
     * @param user пользователь, для которого проверяется разрешение
     * @param task задача, для которой проверяется разрешение
     * @return {@code true}, если пользователь может изменить статус задачи, в противном случае {@code false}
     */
    boolean canChangeStatus(UserEntity user, TaskEntity task);
}
