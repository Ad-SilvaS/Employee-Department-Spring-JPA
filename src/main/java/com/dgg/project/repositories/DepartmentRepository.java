package com.dgg.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgg.project.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
