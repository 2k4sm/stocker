package com.sm2k4.stocker.dtos.Employee;

import com.sm2k4.stocker.models.Department;
import com.sm2k4.stocker.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private String name;
    private String email;
    private Long marketId;
    private Department department;
    private Role role;
}
