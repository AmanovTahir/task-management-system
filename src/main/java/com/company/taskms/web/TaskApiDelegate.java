package com.company.taskms.web;

import com.company.taskms.dto.TaskDto;
import com.company.taskms.dto.request.TasksRequest;
import com.company.taskms.model.enumeration.TaskStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

interface TaskApiDelegate {

    @Operation(
            summary = "Create a task",
            description = "Creates a new task."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class)
            )
    )
    @PostMapping
    ResponseEntity<TaskDto> create(@Valid @RequestBody TaskDto dto);

    @Operation(
            summary = "Get a task by ID",
            description = "Returns a task by its identifier."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class)
            )
    )
    @GetMapping("{taskId}")
    ResponseEntity<TaskDto> find(@Positive @PathVariable @Min(0) Long taskId);

    @Operation(
            summary = "Update a task",
            description = "Updates an existing task.")
    @ApiResponse(responseCode = "200", description = "Task updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class)))
    @PutMapping("{taskId}")
    ResponseEntity<TaskDto> update(@Positive @PathVariable @Min(0) Long taskId,
                                   @Valid @RequestBody TaskDto dto,
                                   @AuthenticationPrincipal Jwt principal);

    @Operation(
            summary = "Get all  tasks",
            description = "Returns a list of all  tasks."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of tasks retrieved successfully",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TaskDto.class))
            )
    )
    @GetMapping("public-tasks")
    ResponseEntity<List<TaskDto>> getAllTasksForUser(@AuthenticationPrincipal Jwt principal);

    @Operation(
            summary = "Delete a task",
            description = "Deletes a task by its identifier.")
    @ApiResponse(
            responseCode = "200",
            description = "Task deleted successfully")
    @DeleteMapping("{taskId}")
    ResponseEntity<Void> delete(@Positive @PathVariable @Min(0) Long taskId,
                                @AuthenticationPrincipal Jwt principal);

    @Operation(
            summary = "Update task status",
            description = "Updates the status of an existing task."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task status updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class)
            )
    )
    @PatchMapping("{taskId}/status")
    ResponseEntity<TaskDto> updateStatus(@Positive @PathVariable @Min(0) Long taskId,
                                         @NotNull @RequestBody TaskStatus status,
                                         @AuthenticationPrincipal Jwt principal);

    @Operation(
            summary = "Assign task to user",
            description = "Assigns a task to a specific user."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task assigned successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class)
            )
    )
    @PatchMapping("{taskId}/assignee")
    ResponseEntity<TaskDto> assignTaskToUser(@Positive @PathVariable @Min(0) Long taskId,
                                             @Positive @RequestParam @Min(0) Long userId,
                                             @AuthenticationPrincipal Jwt principal);

    @Operation(
            summary = "Get tasks created by a specific author",
            description = "Retrieves a paginated list of tasks created by a specific author based on the provided criteria."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of tasks retrieved successfully",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TaskDto.class))
            )
    )
    @PostMapping("author/")
    ResponseEntity<List<TaskDto>> getTasksByAuthor(@RequestBody TasksRequest request);

    @Operation(
            summary = "Get tasks assigned to a specific user",
            description = "Retrieves a paginated list of tasks assigned to a specific user based on the provided criteria."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of tasks retrieved successfully",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TaskDto.class))
            )
    )
    @PostMapping("assignee/")
    ResponseEntity<List<TaskDto>> getTasksByAssignee(@RequestBody TasksRequest request);

}
