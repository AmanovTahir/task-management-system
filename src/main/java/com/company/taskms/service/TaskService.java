package com.company.taskms.service;

import com.company.taskms.model.TaskEntity;
import com.company.taskms.model.UserEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Сервис управления задачами.
 */
public interface TaskService {

    /**
     * Создает новую задачу.
     *
     * @param task Данные новой задачи.
     * @return Созданная задача.
     */
    TaskEntity create(TaskEntity task);

    /**
     * Обновляет существующую задачу.
     *
     * @param updateTask Обновленные данные задачи.
     * @return Обновленная задача.
     */
    TaskEntity update(TaskEntity updateTask);

    /**
     * Удаляет задачу.
     *
     * @param taskId Идентификатор задачи, которую нужно удалить.
     */
    void delete(Long taskId);

    /**
     * Получает задачу по идентификатору.
     *
     * @param taskId Идентификатор задачи.
     * @return Задача с указанным идентификатором.
     */
    TaskEntity getById(Long taskId);

    /**
     * Получает список всех задач.
     *
     * @return Список всех задач.
     */
    List<TaskEntity> getAllTasks();

    /**
     * Получает список задач, связанных с указанным пользователем.
     *
     * @param user Пользователь, для которого нужно получить задачи.
     * @return Список задач, связанных с пользователем.
     */
    List<TaskEntity> getAllTasksByUser(UserEntity user);

    /**
     * Получает задачи, назначенные определенному пользователю, с использованием спецификации и параметров страницы.
     *
     * @param specification Спецификация для фильтрации задач.
     * @param pageRequest   Параметры страницы для пагинации.
     * @return Список задач, отфильтрованных и отсортированных с использованием спецификации и параметров страницы.
     */
    List<TaskEntity> getTasksByAssignee(Specification<TaskEntity> specification, PageRequest pageRequest);

    /**
     * Получает задачи, созданные определенным автором, с использованием спецификации и параметров страницы.
     *
     * @param specification Спецификация для фильтрации задач.
     * @param pageRequest   Параметры страницы для пагинации.
     * @return Список задач, отфильтрованных и отсортированных с использованием спецификации и параметров страницы.
     */
    List<TaskEntity> getTasksByAuthor(Specification<TaskEntity> specification, PageRequest pageRequest);
}
