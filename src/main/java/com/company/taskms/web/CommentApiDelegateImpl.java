package com.company.taskms.web;

import com.company.taskms.dto.CommentDto;
import com.company.taskms.handler.CommentHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Comments", description = "the Comments API")
@RequestMapping("/v1/api/comments/")
public class CommentApiDelegateImpl implements CommentApiDelegate {
    private final CommentHandler commentHandler;

    public ResponseEntity<CommentDto> create(Long taskId, CommentDto dto) {
        log.debug("Request to add a comment to a task. Task ID: {}, Comment: {}", taskId, dto);
        return ResponseEntity.ok(commentHandler.addCommentToTask(taskId, dto));
    }

    public ResponseEntity<CommentDto> find(Long commentId) {
        log.debug("Request to retrieve a comment by ID: {}", commentId);
        return ResponseEntity.ok(commentHandler.getCommentById(commentId));
    }

    public ResponseEntity<CommentDto> update(Long commentId, CommentDto dto) {
        log.debug("Request to update a comment by ID {}: {}", commentId, dto);
        return ResponseEntity.ok(commentHandler.updateComment(commentId, dto));
    }

    public ResponseEntity<Void> delete(Long commentId) {
        log.debug("Request to delete a comment by ID: {}", commentId);
        commentHandler.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<CommentDto>> getCommentsForTask(Long taskId) {
        log.debug("Request to retrieve comments for a task. Task ID: {}", taskId);
        List<CommentDto> comments = commentHandler.getCommentsForTask(taskId);
        return ResponseEntity.ok(comments);
    }
}
