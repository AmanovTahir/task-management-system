package com.company.taskms.mapper;

public abstract class AbstractMapper<D, E> {

    abstract D toDto(E e);

    abstract E toEntity(D d);
}
