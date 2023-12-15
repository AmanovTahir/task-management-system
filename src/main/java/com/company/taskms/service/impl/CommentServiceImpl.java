package com.company.taskms.service.impl;

import com.company.taskms.exception.CommentNotFoundException;
import com.company.taskms.model.CommentEntity;
import com.company.taskms.repository.CommentRepository;
import com.company.taskms.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    public CommentEntity create(CommentEntity comment) {
        log.debug("Creating a new comment: {}", comment);
        return repository.save(comment);
    }

    @Override
    public CommentEntity update(CommentEntity updateComment) {
        log.debug("Updating comment with id {}: {}", updateComment.getId(), updateComment);
        return create(updateComment);
    }

    @Override
    public void delete(Long commentId) {
        log.debug("Deleting comment with id: {}", commentId);
        repository.deleteById(commentId);
    }

    @Override
    public CommentEntity getById(Long commentId) {
        log.debug("Getting comment by id: {}", commentId);
        return repository.findById(commentId).orElseThrow(() ->
                new CommentNotFoundException("Comment not found with id: " + commentId));
    }

    @Override
    public List<CommentEntity> getCommentsForTask(Long taskId) {
        log.debug("Getting comments for task with id: {}", taskId);
        return repository.findAllByTaskId(taskId);
    }
}
