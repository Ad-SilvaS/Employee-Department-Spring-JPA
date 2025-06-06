package com.dgg.project.services;

import org.springframework.stereotype.Service;

import com.dgg.project.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    private EmployeeRepository empRepo;

    public EmployeeService(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

}
