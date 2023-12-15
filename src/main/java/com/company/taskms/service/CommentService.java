package com.company.taskms.service;

import com.company.taskms.model.CommentEntity;

import java.util.List;

public interface CommentService {

    /**
     * Создает новый комментарий к задаче.
     *
     * @param comment Данные нового комментария.
     * @return Созданный комментарий.
     */
    CommentEntity create(CommentEntity comment);

    /**
     * Обновляет существующий комментарий.
     *
     * @param updateComment Обновленные данные комментария.
     * @return Обновленный комментарий.
     */
    CommentEntity update(CommentEntity updateComment);

    /**
     * Удаляет комментарий к задаче.
     *
     * @param commentId Идентификатор комментария, который нужно удалить.
     */
    void delete(Long commentId);

    /**
     * Получает комментарий по идентификатору.
     *
     * @param commentId Идентификатор комментария.
     * @return Комментарий с указанным идентификатором.
     */
    CommentEntity getById(Long commentId);

    /**
     * Получает все комментарии для задачи.
     *
     * @param taskId Идентификатор задачи.
     * @return Список комментариев для задачи.
     */
    List<CommentEntity> getCommentsForTask(Long taskId);
}
