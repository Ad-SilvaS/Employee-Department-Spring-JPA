package com.dgg.project.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgg.project.DTO.DepartmentDTO;
import com.dgg.project.entities.Department;
import com.dgg.project.repositories.DepartmentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DepartmentService {
    private DepartmentRepository depRepo;

    public DepartmentService(DepartmentRepository depRepo) {
        this.depRepo = depRepo;
    }

    private DepartmentDTO convertToDTO(Department dep) {
        return new DepartmentDTO(dep.getId(), dep.getName());
    }

    @Transactional
    public Department newDepartment(DepartmentDTO depDTO) {
        Department dep = new Department(depDTO.getName());

        return depRepo.save(dep);
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findAll() {
        return depRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    @Transactional(readOnly = true)
    public DepartmentDTO findById(Integer id) {
        Department dep = depRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Department Not Found"));

        return convertToDTO(dep);
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findByName(String name) {
        return depRepo.findByNameContainingIgnoreCase(name).stream().map(this::convertToDTO).toList();
    }

    @Transactional
    public DepartmentDTO updateName(Integer id, String name) {
        Department dep = depRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Department Not Found"));

        dep.setName(name);

        return convertToDTO(dep);
    }
}