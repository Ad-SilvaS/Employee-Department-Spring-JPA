package com.dgg.project.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dgg.project.DTO.DepartmentDTO;
import com.dgg.project.services.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public DepartmentDTO newDepartment(@Valid @RequestBody DepartmentDTO depDTO) {
        return service.newDepartment(depDTO);
    }

    @GetMapping
    public List<DepartmentDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/name")
    public List<DepartmentDTO> findByName(@RequestParam String name) {
        return service.findByName(name);
    }

    @PutMapping("/{id}/update-name")
    public DepartmentDTO updateName(@PathVariable Integer id, @RequestParam String name) {
        return service.updateName(id, name);
    }

    @DeleteMapping("/{id}")
    public List<DepartmentDTO> deleteDepartment(@PathVariable Integer id) {
        return service.deleteDepartment(id);
    }
}