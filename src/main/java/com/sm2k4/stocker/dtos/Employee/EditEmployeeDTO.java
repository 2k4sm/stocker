package com.sm2k4.stocker.dtos.Employee;

import com.sm2k4.stocker.models.Employee;
import com.sm2k4.stocker.models.Market;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EditEmployeeDTO {

    public Employee EditEmployeeDTO(){
        Employee employee = new Employee();
        employee.setName(name);
        employee.setMarketId(marketId);
        employee.setEmail(email);
        return employee;

    }

    private String name;
    private String email;
    private Market marketId;
    
}
