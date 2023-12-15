package com.company.taskms.exception;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String s) {
        super(s);
    }
}
