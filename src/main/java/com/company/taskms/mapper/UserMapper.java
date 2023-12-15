package com.company.taskms.mapper;

import com.company.taskms.dto.UserDto;
import com.company.taskms.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper extends AbstractMapper<UserDto, UserEntity> {

    @Override
    UserDto toDto(UserEntity userEntity) {
        return new UserDto(userEntity.getName(), userEntity.getEmail());
    }

    @Override
    UserEntity toEntity(UserDto userDto) {
        return new UserEntity(userDto.getName(), userDto.getEmail());
    }
}
