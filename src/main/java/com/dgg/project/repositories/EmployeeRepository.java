package com.dgg.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgg.project.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
