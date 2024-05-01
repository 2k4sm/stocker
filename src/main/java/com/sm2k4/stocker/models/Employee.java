package com.sm2k4.stocker.models;

import com.sm2k4.stocker.dtos.Employee.CreateEmployeeDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false,updatable = false)
    private Department department;
    @Column(nullable = false,updatable = false)
    private Role role;
    @ManyToOne
    private Market marketId;

}
