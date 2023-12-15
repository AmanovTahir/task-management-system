package com.company.taskms.repository;

import com.company.taskms.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>,
        PagingAndSortingRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByTaskId(Long taskId);
}
