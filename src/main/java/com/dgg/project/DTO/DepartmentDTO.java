package com.dgg.project.DTO;

import jakarta.validation.constraints.NotBlank;

public class DepartmentDTO {
    private Integer id;

    @NotBlank
    private String name;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Integer id, @NotBlank String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}