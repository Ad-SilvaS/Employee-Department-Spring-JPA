package com.dgg.project.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgg.project.DTO.EmployeeDTO;
import com.dgg.project.entities.Department;
import com.dgg.project.entities.Employee;
import com.dgg.project.repositories.DepartmentRepository;
import com.dgg.project.repositories.EmployeeRepository;
import com.dgg.project.services.exception.NotFoundException;

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
    public EmployeeDTO newEmployee(EmployeeDTO empDTO) {
        Department department = depRepo.findById(empDTO.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department Not Found"));

        Employee emp = new Employee(
                empDTO.getName(),
                empDTO.getEmail(),
                empDTO.getPhone(),
                empDTO.getBirthDate(),
                department);

        Employee newEmp = empRepo.save(emp);

        return convertToDTO(newEmp);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> findAll() {
        List<Employee> empList = empRepo.findAll();

        if (empList.isEmpty()) {
            throw new NotFoundException();
        }

        return empList.stream().map(this::convertToDTO).toList();
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findById(Long id) {
        Employee emp = empRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        return convertToDTO(emp);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> findByName(String name) {
        List<Employee> empList = empRepo.findByNameContainingIgnoreCase(name);

        if (empList.isEmpty()) {
            throw new NotFoundException(name);
        }

        return empList.stream().map(this::convertToDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> findByEmail(String email) {
        List<Employee> empList = empRepo.findByEmailContainingIgnoreCase(email);

        if (empList.isEmpty()) {
            throw new NotFoundException(email);
        }

        return empList.stream().map(this::convertToDTO).toList();
    }

    @Transactional
    public EmployeeDTO updateName(Long id, String name) {
        Employee emp = empRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        emp.setName(name);

        return convertToDTO(emp);
    }

    @Transactional
    public EmployeeDTO updateEmail(Long id, String email) {
        Employee emp = empRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        emp.setEmail(email);

        return convertToDTO(emp);
    }

    @Transactional
    public EmployeeDTO updatePhone(Long id, String phone) {
        Employee emp = empRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        emp.setPhone(phone);

        return convertToDTO(emp);
    }

    @Transactional
    public EmployeeDTO alterDepartment(Long id, Integer departmentId) {
        Employee emp = empRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        Department dep = depRepo.findById(departmentId)
                .orElseThrow(() -> new NotFoundException(departmentId));

        emp.setDepartment(dep);

        empRepo.save(emp);

        return convertToDTO(emp);
    }

    @Transactional
    public List<EmployeeDTO> deleteEmployee(Long id) {
        Employee emp = empRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        empRepo.delete(emp);

        return findAll();
    }
}