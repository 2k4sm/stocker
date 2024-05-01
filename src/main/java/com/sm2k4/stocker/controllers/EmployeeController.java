package com.sm2k4.stocker.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm2k4.stocker.dtos.Employee.CreateEmployeeDTO;
import com.sm2k4.stocker.dtos.Employee.EditEmployeeDTO;
import com.sm2k4.stocker.models.Employee;
import com.sm2k4.stocker.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    
    @GetMapping("")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable Long id){
        return employeeService.getEmployeeByID(id);
    }

    @PostMapping("")
    public Employee createEmployee(@RequestBody CreateEmployeeDTO CreateEmployeeDTO){
        return employeeService.createEmployee(CreateEmployeeDTO);
    }

    @PutMapping("/{id}")
    public Employee updatEmployee(@PathVariable Long id, @RequestBody EditEmployeeDTO editEmployeeDTO ){
        return employeeService.updateEmployee(id,editEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }
    

    

}
