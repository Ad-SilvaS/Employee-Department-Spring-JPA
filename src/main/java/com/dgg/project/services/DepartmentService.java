package com.dgg.project.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Department newDepartment(DepartmentDTO depDTO) {
        Department dep = new Department(depDTO.getName());

        return depRepo.save(dep);
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findAll() {
        return depRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
