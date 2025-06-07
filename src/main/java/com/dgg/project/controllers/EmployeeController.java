package com.dgg.project.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgg.project.DTO.EmployeeDTO;
import com.dgg.project.entities.Employee;
import com.dgg.project.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Employee newEmployee(@Valid @RequestBody EmployeeDTO empDTO) {
        return service.newEmployee(empDTO);
    }

    @GetMapping
    public List<EmployeeDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
