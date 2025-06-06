package com.dgg.project.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgg.project.DTO.EmployeeDTO;
import com.dgg.project.entities.Department;
import com.dgg.project.entities.Employee;
import com.dgg.project.repositories.DepartmentRepository;
import com.dgg.project.repositories.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {
    private EmployeeRepository empRepo;
    private DepartmentRepository depRepo;

    public EmployeeService(EmployeeRepository empRepo, DepartmentRepository depRepo) {
        this.empRepo = empRepo;
        this.depRepo = depRepo;
    }

    private EmployeeDTO convertToDTO(Employee emp) {
        return new EmployeeDTO(
                emp.getName(),
                emp.getEmail(),
                emp.getPhone(),
                emp.getBirthDate(),
                emp.getDepartment().getId());
    }

    @Transactional
    public Employee newEmployee(EmployeeDTO empDTO) {
        Department department = depRepo.findById(empDTO.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department Not Found"));

        Employee emp = new Employee(
                empDTO.getName(),
                empDTO.getEmail(),
                empDTO.getPhone(),
                empDTO.getBirthDate(),
                department);

        return empRepo.save(emp);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> findAll() {
        return empRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
