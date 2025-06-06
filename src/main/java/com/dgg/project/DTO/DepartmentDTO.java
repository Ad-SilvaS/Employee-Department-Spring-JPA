package com.dgg.project.DTO;

import jakarta.validation.constraints.NotBlank;

public class DepartmentDTO {
    @NotBlank
    private String name;

    public DepartmentDTO() {
    }

    public DepartmentDTO(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
