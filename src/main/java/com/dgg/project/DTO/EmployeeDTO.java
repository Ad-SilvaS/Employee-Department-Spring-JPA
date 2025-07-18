package com.dgg.project.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    @NotBlank
    private String departmentName;

    public EmployeeDTO() {
    }

    public EmployeeDTO(@NotNull Long id, @NotBlank String name, @NotBlank String email, @NotBlank String phone,
            @NotNull LocalDate birthDate, @NotNull String departmentName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getdepartmentName() {
        return departmentName;
    }

    public void setdepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}