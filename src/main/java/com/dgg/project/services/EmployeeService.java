package com.dgg.project.services;

import org.springframework.stereotype.Service;

import com.dgg.project.DTO.EmployeeDTO;
import com.dgg.project.entities.Employee;
import com.dgg.project.repositories.DepartmentRepository;
import com.dgg.project.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    private EmployeeRepository empRepo;
    private DepartmentRepository depRepo;

    public EmployeeService(EmployeeRepository empRepo, DepartmentRepository depRepo) {
        this.empRepo = empRepo;
        this.depRepo = depRepo;
    }

    public EmployeeDTO convertToDTO(Employee emp) {
        return new EmployeeDTO(
                emp.getName(),
                emp.getEmail(),
                emp.getPhone(),
                emp.getBirthDate(),
                emp.getDepartment().getId());
    }
}
