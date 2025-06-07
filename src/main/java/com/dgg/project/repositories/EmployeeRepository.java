package com.dgg.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgg.project.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findByNameContainingIgnoreCase(String name);
}
