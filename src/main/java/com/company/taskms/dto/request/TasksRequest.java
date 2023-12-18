package com.company.taskms.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(name = "TasksRequest", description = "Request to retrieve a paginated list of tasks based on specified criteria")
public class TasksRequest {
    @Schema(
            name = "userId",
            example = "1",
            description = "User unique id",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Positive
    @Min(0)
    private long userId;

    @JsonProperty("page")
    @Schema(
            name = "page",
            example = "0",
            defaultValue = "0",
            description = "Page number for paginated results"
    )
    @Builder.Default
    private int page = 0;

    @JsonProperty("size")
    @Schema(
            name = "size",
            example = "20",
            defaultValue = "20",
            description = "Number of tasks per page"
    )
    @Builder.Default
    @Positive(message = "Size must be greater than 0")
    private int size = 20;

    @Schema(
            name = "filterRequest",
            description = "Filter criteria for tasks",
            implementation = FilterRequest.class
    )
    private FilterRequest filterRequest;
}
