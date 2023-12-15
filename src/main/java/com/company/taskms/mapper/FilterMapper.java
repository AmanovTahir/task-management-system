package com.company.taskms.mapper;

import com.company.taskms.dto.request.FilterRequest;
import com.company.taskms.dto.request.TasksRequest;
import com.company.taskms.model.TaskEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class FilterMapper {

    public Specification<TaskEntity> specification(TasksRequest request, String permission) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            FilterRequest filterRequest = request.getFilterRequest();

            predicates.add(cb.equal(root.get(permission).get("id"), request.getUserId()));
            if (Objects.nonNull(filterRequest.getHeader())) {
                predicates.add(cb.like(root.get("header"), filterRequest.getHeader()));
            }
            if (Objects.nonNull(filterRequest.getDescription())) {
                predicates.add(cb.like(root.get("description"), filterRequest.getDescription()));
            }
            if (Objects.nonNull(filterRequest.getStatus())) {
                predicates.add(cb.equal(root.get("status"), filterRequest.getStatus()));
            }
            if (Objects.nonNull(filterRequest.getPriority())) {
                predicates.add(cb.equal(root.get("priority"), filterRequest.getPriority()));
            }
            if (Objects.nonNull(filterRequest.getAuthorEmail())) {
                predicates.add(cb.like(root.get("author").get("email"), filterRequest.getAuthorEmail()));
            }
            if (Objects.nonNull(filterRequest.getAssigneeEmail())) {
                predicates.add(cb.like(root.get("assignee").get("email"), filterRequest.getAssigneeEmail()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
