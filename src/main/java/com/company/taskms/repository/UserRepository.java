package com.company.taskms.repository;

import com.company.taskms.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>,
        PagingAndSortingRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByEmail(String email);
}
