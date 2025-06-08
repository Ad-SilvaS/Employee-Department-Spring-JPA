package com.dgg.project.services.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("No results!");
    }

    public NotFoundException(Long id) {
        super("Employee not found! Id: " + id);
    }

    public NotFoundException(String str) {
        super("Not found! No results for: " + str);
    }

    public NotFoundException(Integer id) {
        super("Department not found! Id: " + id);
    }
}
