package com.company.taskms.web;

import com.company.taskms.core.TestCore;
import com.company.taskms.core.TestUtil;
import com.company.taskms.dto.TaskDto;
import com.company.taskms.dto.UserDto;
import com.company.taskms.dto.request.FilterRequest;
import com.company.taskms.dto.request.TasksRequest;
import com.company.taskms.exception.TaskNotFoundException;
import com.company.taskms.model.TaskEntity;
import com.company.taskms.model.enumeration.PriorityType;
import com.company.taskms.model.enumeration.TaskStatus;
import com.company.taskms.service.TaskService;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class TaskApiDelegateImplIntegrationTest extends TestCore {

    @Autowired
    private TaskService taskService;

    @Test
    @SneakyThrows
    void testCreateTask() {
        // given
        var content = TestUtil.convertObjectToJsonBytes(getTaskDto());
        var jwt = mockJwt();

        // when and then
        mockMvc.perform(post("/v1/api/tasks/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(jwt))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.header").value("header"))
                .andExpect(jsonPath("$.description").value("description"));
    }

    @Test
    void testGetTaskById() throws Exception {
        // given
        var jwt = mockJwt();
        TaskEntity entity = taskService.getById(1L);

        // when and then
        mockMvc.perform(get("/v1/api/tasks/30")
                        .with(jwt))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTask() throws Exception {
        // given
        var content = TestUtil.convertObjectToJsonBytes(getTaskDto());
        Jwt principal = Mockito.mock(Jwt.class);
        when(principal.getClaim("email")).thenReturn("user1@example.com");

        // when and then
        mockMvc.perform(put("/v1/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(jwt().jwt(principal)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.header").value("header"))
                .andExpect(jsonPath("$.description").value("description"));
    }

    @Test
    void testGetAllTasksForUser() throws Exception {
        // given
        Jwt principal = Mockito.mock(Jwt.class);
        when(principal.getClaim("email")).thenReturn("user1@example.com");

        // when and then
        mockMvc.perform(get("/v1/api/tasks/public-tasks")
                        .with(jwt().jwt(principal)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)));
    }


    @Test
    void testDeleteTask() throws Exception {
        // given
        Jwt principal = Mockito.mock(Jwt.class);
        when(principal.getClaim("email")).thenReturn("user1@example.com");

        // when and then
        mockMvc.perform(delete("/v1/api/tasks/1")
                        .with(jwt().jwt(principal)))
                .andDo(print())
                .andExpect(status().isOk());

        Assertions.assertThrows(TaskNotFoundException.class, () -> taskService.getById(1L));
    }

    @Test
    void testUpdateTaskStatus() throws Exception {
        // given
        var content = TestUtil.convertObjectToJsonBytes(TaskStatus.COMPLETED);
        Jwt principal = Mockito.mock(Jwt.class);
        when(principal.getClaim("email")).thenReturn("user1@example.com");

        // when and then
        mockMvc.perform(patch("/v1/api/tasks/2/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(jwt().jwt(principal)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(TaskStatus.COMPLETED.name()));
    }

    @Test
    void testAssignTaskToUser() throws Exception {
        // given
        Jwt principal = Mockito.mock(Jwt.class);
        when(principal.getClaim("email")).thenReturn("user1@example.com");

        // when and then
        mockMvc.perform(patch("/v1/api/tasks/4/assignee")
                        .param("userId", "1")
                        .with(jwt().jwt(principal)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.assignee.email").value("user1@example.com"));
    }

    @Test
    void testGetTaskByAuthor() throws Exception {
        // given
        FilterRequest filterRequest = getFilterRequest();
        TasksRequest tasksRequest = new TasksRequest(1L, 0, 20, filterRequest);
        var content = TestUtil.convertObjectToJsonBytes(tasksRequest);
        Jwt principal = Mockito.mock(Jwt.class);
        when(principal.getClaim("email")).thenReturn("user1@example.com");

        // when and then
        mockMvc.perform(post("/v1/api/tasks/author/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(jwt().jwt(principal)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testGetTaskByAssignee() throws Exception {
        // given
        FilterRequest filterRequest = getFilterRequest();
        TasksRequest tasksRequest = new TasksRequest(1L, 0, 20, filterRequest);
        var content = TestUtil.convertObjectToJsonBytes(tasksRequest);
        Jwt principal = Mockito.mock(Jwt.class);
        when(principal.getClaim("email")).thenReturn("user1@example.com");

        // when and then
        mockMvc.perform(post("/v1/api/tasks/assignee/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(jwt().jwt(principal)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @NotNull
    private FilterRequest getFilterRequest() {
        return FilterRequest.builder()
                .status(TaskStatus.IN_PROGRESS)
                .build();
    }

    @NotNull
    private TaskDto getTaskDto() {
        UserDto userDto = new UserDto("User1", "user1@example.com");
        UserDto assignee = new UserDto("User1", "user1@example.com");
        return new TaskDto("header", "description",
                TaskStatus.IN_PROGRESS, PriorityType.HIGH, userDto, assignee);
    }
}
