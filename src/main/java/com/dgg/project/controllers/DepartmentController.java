package com.dgg.project.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgg.project.DTO.DepartmentDTO;
import com.dgg.project.entities.Department;
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
    public Department newDepartment(@Valid @RequestBody DepartmentDTO depDTO) {
        return service.newDepartment(depDTO);
    }
}
