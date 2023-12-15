package com.company.taskms.service;

import com.company.taskms.model.UserEntity;

/**
 * Сервис управления пользователями.
 */
public interface UserService {

    /**
     * Получает пользователя по идентификатору.
     *
     * @param userId Идентификатор пользователя.
     * @return Пользователь с указанным идентификатором.
     */
    UserEntity getById(Long userId);

    /**
     * Получает пользователя по электронной почте.
     *
     * @param email Электронная почта пользователя.
     * @return Пользователь с указанным адресом электронной почты.
     */
    UserEntity getByEmail(String email);
}
