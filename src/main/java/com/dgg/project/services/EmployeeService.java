package com.dgg.project.services;

import java.util.List;

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
                emp.getId(),
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
        return empRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findById(Long id) {
        Employee emp = empRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee Not Found"));

        return convertToDTO(emp);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> findByName(String name) {
        return empRepo.findByNameContainingIgnoreCase(name).stream().map(this::convertToDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> findByEmail(String email) {
        return empRepo.findByEmailContainingIgnoreCase(email).stream().map(this::convertToDTO).toList();
    }
}