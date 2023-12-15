package com.company.taskms.handler;

import com.company.taskms.dto.CommentDto;

import java.util.List;

/**
 * Обработчик управления комментариями к задачам.
 */
public interface CommentHandler {

    /**
     * Добавляет комментарий к задаче.
     *
     * @param taskId     Идентификатор задачи.
     * @param commentDto Данные нового комментария.
     * @return Созданный комментарий.
     */
    CommentDto addCommentToTask(Long taskId, CommentDto commentDto);

    /**
     * Обновляет существующий комментарий.
     *
     * @param commentId         Идентификатор комментария, который нужно обновить.
     * @param updatedCommentDto Обновленные данные комментария.
     * @return Обновленный комментарий.
     */
    CommentDto updateComment(Long commentId, CommentDto updatedCommentDto);

    /**
     * Удаляет комментарий к задаче.
     *
     * @param commentId Идентификатор комментария, который нужно удалить.
     */
    void deleteComment(Long commentId);

    /**
     * Получает комментарий по идентификатору.
     *
     * @param commentId Идентификатор комментария.
     * @return Комментарий с указанным идентификатором.
     */
    CommentDto getCommentById(Long commentId);

    /**
     * Получает все комментарии для задачи.
     *
     * @param taskId Идентификатор задачи.
     * @return Список комментариев для задачи.
     */
    List<CommentDto> getCommentsForTask(Long taskId);
}
