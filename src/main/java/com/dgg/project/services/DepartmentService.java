package com.dgg.project.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgg.project.DTO.DepartmentDTO;
import com.dgg.project.entities.Department;
import com.dgg.project.repositories.DepartmentRepository;
import com.dgg.project.services.exception.NotFoundException;

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
    public DepartmentDTO newDepartment(DepartmentDTO depDTO) {
        Department dep = new Department(depDTO.getName());

        depRepo.save(dep);

        return convertToDTO(dep);
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findAll() {
        List<Department> depList = depRepo.findAll();

        if (depList.isEmpty()) {
            throw new NotFoundException();
        }

        return depList.stream().map(this::convertToDTO).toList();
    }

    @Transactional(readOnly = true)
    public DepartmentDTO findById(Integer id) {
        Department dep = depRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        return convertToDTO(dep);
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findByName(String name) {
        List<Department> depList = depRepo.findByNameContainingIgnoreCase(name);

        if (depList.isEmpty()) {
            throw new NotFoundException(name);
        }

        return depList.stream().map(this::convertToDTO).toList();
    }

    @Transactional
    public DepartmentDTO updateName(Integer id, String name) {
        Department dep = depRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        dep.setName(name);

        return convertToDTO(dep);
    }

    @Transactional
    public List<DepartmentDTO> deleteDepartment(Integer id) {
        Department dep = depRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        depRepo.delete(dep);

        return findAll();
    }
}