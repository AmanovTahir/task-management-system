package com.company.taskms.mapper;

import com.company.taskms.dto.CommentDto;
import com.company.taskms.model.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper extends AbstractMapper<CommentDto, CommentEntity> {

    public CommentDto toDto(CommentEntity entity) {
        return new CommentDto(entity.getComment());
    }

    public CommentEntity toEntity(CommentDto dto) {
        return new CommentEntity(dto.getComment());
    }
}
