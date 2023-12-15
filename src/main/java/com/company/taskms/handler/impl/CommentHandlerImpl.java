package com.company.taskms.handler.impl;

import com.company.taskms.dto.CommentDto;
import com.company.taskms.handler.CommentHandler;
import com.company.taskms.mapper.CommentMapper;
import com.company.taskms.model.CommentEntity;
import com.company.taskms.model.TaskEntity;
import com.company.taskms.service.CommentService;
import com.company.taskms.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentHandlerImpl implements CommentHandler {

    private final CommentService commentService;
    private final TaskService taskService;
    private final CommentMapper mapper;

    @Override
    public CommentDto addCommentToTask(Long taskId, CommentDto commentDto) {
        TaskEntity task = taskService.getById(taskId);
        CommentEntity comment = new CommentEntity(task, commentDto.getComment());
        return mapper.toDto(commentService.create(comment));
    }

    @Override
    public CommentDto updateComment(Long commentId, CommentDto updatedCommentDto) {
        CommentEntity comment = commentService.getById(commentId);
        comment.setComment(updatedCommentDto.getComment());
        return mapper.toDto(commentService.update(comment));
    }

    @Override
    public void deleteComment(Long commentId) {
        commentService.delete(commentId);
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        return mapper.toDto(commentService.getById(commentId));
    }

    @Override
    public List<CommentDto> getCommentsForTask(Long taskId) {
        return commentService.getCommentsForTask(taskId).stream().map(mapper::toDto).toList();
    }
}
