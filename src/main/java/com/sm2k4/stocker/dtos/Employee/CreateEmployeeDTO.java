package com.sm2k4.stocker.dtos.Employee;

import com.sm2k4.stocker.models.Department;
import com.sm2k4.stocker.models.Employee;
import com.sm2k4.stocker.models.Market;
import com.sm2k4.stocker.models.Role;

import jakarta.persistence.Id;
import lombok.Getter;

import lombok.Setter;
/**
 * EmployeeDTO
 */
@Getter
@Setter
public class CreateEmployeeDTO {
    private String name;
    private String email;
    private Long marketId;
    private Department department;
    private Role role;
}