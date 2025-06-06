package com.dgg.project.services;

import org.springframework.stereotype.Service;

import com.dgg.project.DTO.DepartmentDTO;
import com.dgg.project.entities.Department;
import com.dgg.project.repositories.DepartmentRepository;

@Service
public class DepartmentService {
    private DepartmentRepository depRepo;

    public DepartmentService(DepartmentRepository depRepo) {
        this.depRepo = depRepo;
    }

    private DepartmentDTO convertToDTO(Department dep) {
        return new DepartmentDTO(dep.getName());
    }

    public Department newDepartment(DepartmentDTO depDTO) {
        Department dep = new Department(depDTO.getName());

        return depRepo.save(dep);
    }
}
