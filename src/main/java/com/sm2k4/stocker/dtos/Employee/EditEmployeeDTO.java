package com.sm2k4.stocker.dtos.Employee;

import com.sm2k4.stocker.models.Employee;
import com.sm2k4.stocker.models.Market;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EditEmployeeDTO {
    private String name;
    private String email;
    private Long marketId;
}
