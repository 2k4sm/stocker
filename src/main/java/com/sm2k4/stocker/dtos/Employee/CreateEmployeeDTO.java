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
    
    public Employee createEmployeeDTO(){

        Employee employee = new Employee();
        employee.setName(name);
        employee.setMarketId(marketId);
        employee.setEmail(email);
        //employee id kaise banega
        employee.setDepartment(department);
        return employee;

    }

    private String name;
    private String email;
    private Market marketId;
    private Department department;
    private Role role;

}