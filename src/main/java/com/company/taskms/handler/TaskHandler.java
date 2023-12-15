package com.company.taskms.handler;

import com.company.taskms.dto.TaskDto;
import com.company.taskms.dto.request.TasksRequest;
import com.company.taskms.model.enumeration.TaskStatus;

import java.util.List;

/**
 * Обработчик управления задачами.
 */
public interface TaskHandler {

    /**
     * Создает новую задачу.
     *
     * @param task Данные новой задачи.
     * @return Созданная задача.
     */
    TaskDto createTask(TaskDto task);

    /**
     * Обновляет существующую задачу.
     *
     * @param principal   Имя пользователя (авторизованного) выполняющего операцию.
     * @param taskId      Идентификатор задачи, которую нужно обновить.
     * @param updatedTask Обновленная информация о задаче.
     * @return Обновленная задача.
     */
    TaskDto updateTask(String principal, Long taskId, TaskDto updatedTask);

    /**
     * Удаляет задачу по идентификатору.
     *
     * @param principal Имя пользователя (авторизованного) выполняющего операцию.
     * @param taskId    Идентификатор задачи, которую нужно удалить.
     */
    void deleteTask(String principal, Long taskId);

    /**
     * Получает задачу по идентификатору.
     *
     * @param taskId Идентификатор задачи.
     * @return Задача с указанным идентификатором.
     */
    TaskDto getTaskById(Long taskId);

    /**
     * Обновляет статус задачи.
     *
     * @param principal Имя пользователя (авторизованного) выполняющего операцию.
     * @param taskId    Идентификатор задачи, статус которой нужно обновить.
     * @param status    Новый статус задачи.
     * @return Обновленная задача с новым статусом.
     */
    TaskDto updateTaskStatus(String principal, Long taskId, TaskStatus status);

    /**
     * Получает все задачи.
     *
     * @return Список всех задач.
     */
    List<TaskDto> getAllTasks();

    /**
     * Получает все задачи конкретного пользователя (автора) с учетом фильтрации и пагинации.
     *
     * @param request Запрос на получение задач.
     * @return Список задач автора.
     */
    List<TaskDto> getTasksByAuthor(TasksRequest request);

    /**
     * Получает все задачи, назначенные конкретному пользователю (исполнителю) с учетом фильтрации и пагинации.
     *
     * @param request Запрос на получение задач.
     * @return Список задач, назначенных пользователю.
     */
    List<TaskDto> getTasksByAssignee(TasksRequest request);

    /**
     * Добавляет исполнителя к задаче.
     *
     * @param email  Электронная почта исполнителя.
     * @param taskId Идентификатор задачи.
     * @param userId Идентификатор пользователя (исполнителя).
     * @return Обновленная задача с добавленным исполнителем.
     */
    TaskDto addAssignee(String email, Long taskId, Long userId);

    /**
     * Получает все задачи для конкретного пользователя с учетом фильтрации и пагинации.
     *
     * @param email Электронная почта пользователя.
     * @return Список задач для пользователя.
     */
    List<TaskDto> getAllTasksForUser(String email);
}
