package com.company.taskms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "UserDto", description = "Representation of a user")
public class UserDto {

    @NotBlank(message = "name cannot be blank")
    @Schema(description = "User name", defaultValue = "User 1")
    private String name;

    @NotBlank(message = "email cannot be blank")
    @Schema(description = "User email", defaultValue = "user1@example.com")
    private String email;
}
