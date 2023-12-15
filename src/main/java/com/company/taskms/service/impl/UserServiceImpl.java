package com.company.taskms.service.impl;

import com.company.taskms.exception.UserNotFoundException;
import com.company.taskms.model.UserEntity;
import com.company.taskms.repository.UserRepository;
import com.company.taskms.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public UserEntity getById(Long userId) {
        log.debug("Getting user by id: {}", userId);
        return repository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User not found with id: " + userId));
    }

    @Override
    public UserEntity getByEmail(String email) {
        log.debug("Getting user by email: {}", email);
        return repository.findUserEntityByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User not found with email: " + email));
    }
}
