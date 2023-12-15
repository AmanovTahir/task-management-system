package com.company.taskms.repository;

import com.company.taskms.model.TaskEntity;
import com.company.taskms.model.UserEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>, JpaSpecificationExecutor<TaskEntity> {

    List<TaskEntity> findAllByAuthor(UserEntity user);

    List<TaskEntity> findAllByAuthor_Id(Long userId, PageRequest request);
}
