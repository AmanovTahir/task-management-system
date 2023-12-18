package com.company.taskms.web;

import com.company.taskms.core.TestCore;
import com.company.taskms.core.TestUtil;
import com.company.taskms.dto.CommentDto;
import com.company.taskms.exception.CommentNotFoundException;
import com.company.taskms.model.CommentEntity;
import com.company.taskms.service.CommentService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class CommentApiDelegateImplIntegrationTest extends TestCore {
    @Autowired
    private CommentService commentService;

    @SneakyThrows
    @Test
    void create() {
        // given
        CommentDto comment = new CommentDto("comment");
        var content = TestUtil.convertObjectToJsonBytes(comment);
        var jwt = mockJwt();

        // when and then
        mockMvc.perform(post("/v1/api/comments/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(jwt))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.comment").value("comment"));

        Assertions.assertTrue(commentService.getCommentsForTask(1L)
                .stream().map(CommentEntity::getComment)
                .toList().contains("comment"));
    }

    @SneakyThrows
    @Test
    void find() {
        // given
        var jwt = mockJwt();

        // when and then
        mockMvc.perform(get("/v1/api/comments/1")
                        .with(jwt))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.comment").value("Comment 1 for Task 1"));
    }

    @SneakyThrows
    @Test
    void update() {
        // given
        CommentDto comment = new CommentDto("comment");
        var content = TestUtil.convertObjectToJsonBytes(comment);
        var jwt = mockJwt();

        // when and then
        mockMvc.perform(put("/v1/api/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(jwt))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.comment").value("comment"));
    }

    @SneakyThrows
    @Test
    void deleteTest() {
        // given
        var jwt = mockJwt();

        // when and then
        mockMvc.perform(delete("/v1/api/comments/1")
                        .with(jwt))
                .andDo(print())
                .andExpect(status().isOk());

        Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.getById(1L));
    }

    @SneakyThrows
    @Test
    void getCommentsForTask() {
        // given
        var jwt = mockJwt();

        // when and then
        mockMvc.perform(get("/v1/api/comments/task/1")
                        .with(jwt))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }
}