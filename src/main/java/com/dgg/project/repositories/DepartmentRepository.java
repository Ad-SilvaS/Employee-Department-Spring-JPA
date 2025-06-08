package com.dgg.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgg.project.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    public List<Department> findByNameContainingIgnoreCase(String name);
}