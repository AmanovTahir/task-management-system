package com.company.taskms.web;

import com.company.taskms.dto.CommentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

interface CommentApiDelegate {

    @Operation(summary = "Add a comment to a task", description = "Adds a new comment to a task.")
    @ApiResponse(responseCode = "200", description = "Comment added successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDto.class)))
    @PostMapping("tasks/{taskId}")
    ResponseEntity<CommentDto> create(@Positive @PathVariable @Min(0) Long taskId, @RequestBody CommentDto dto);

    @Operation(summary = "Get a comment by ID", description = "Returns a comment by its identifier.")
    @ApiResponse(responseCode = "200", description = "Comment retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDto.class)))
    @GetMapping("{commentId}")
    ResponseEntity<CommentDto> find(@Positive @PathVariable @Min(0) Long commentId);

    @Operation(summary = "Update a comment", description = "Updates an existing comment.")
    @ApiResponse(responseCode = "200", description = "Comment updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDto.class)))
    @PutMapping("{commentId}")
    ResponseEntity<CommentDto> update(@Positive @PathVariable @Min(0) Long commentId,
                                      @Valid @RequestBody CommentDto dto);

    @Operation(summary = "Delete a comment", description = "Deletes a comment by its identifier.")
    @ApiResponse(responseCode = "200", description = "Comment deleted successfully")
    @DeleteMapping("{commentId}")
    ResponseEntity<Void> delete(@Positive @PathVariable @Min(0) Long commentId);

    @Operation(summary = "Get comments for a task", description = "Returns a list of comments for a specific task.")
    @ApiResponse(responseCode = "200", description = "List of comments retrieved successfully",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CommentDto.class))))
    @GetMapping("task/{taskId}")
    ResponseEntity<List<CommentDto>> getCommentsForTask(@Positive @PathVariable @Min(0) Long taskId);
}
