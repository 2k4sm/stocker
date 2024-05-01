package com.sm2k4.stocker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sm2k4.stocker.dtos.Employee.CreateEmployeeDTO;
import com.sm2k4.stocker.dtos.Employee.EditEmployeeDTO;
import com.sm2k4.stocker.exceptions.GeneralExceptions.AlreadyExistsException;
import com.sm2k4.stocker.exceptions.GeneralExceptions.BadRequestException;
import com.sm2k4.stocker.exceptions.GeneralExceptions.NotFoundException;
import com.sm2k4.stocker.models.Department;
import com.sm2k4.stocker.models.Employee;
import com.sm2k4.stocker.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    // get all emp, get emp by id;  create, update, delete emp
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    public Employee getEmployeeByID(long id){
        if(employeeRepository.findById(id).isEmpty())
        {
             throw new NotFoundException("Employee not found");
        }
        

        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(CreateEmployeeDTO createEmployeeDTO){
        if(createEmployeeDTO.getName() == null || createEmployeeDTO.getEmail() == null 
        || createEmployeeDTO.getDepartment() == null || 
        createEmployeeDTO.getRole() == null || createEmployeeDTO.getMarketId() == null )
        {
            throw new BadRequestException("Some field of employee is null");
        }
        if(createEmployeeDTO.getDepartment()!= Department.TRADING || createEmployeeDTO.getDepartment() != Department.REGULATION) 
        {
            throw new BadRequestException("The department is invalid");
        }

        
        
        return employeeRepository.save(createEmployeeDTO.createEmployeeDTO());
    }

    public void deleteEmployee(Long id){
        if(employeeRepository.findById(id).isEmpty())
        { 
            throw new NotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(long id, EditEmployeeDTO editEmployeeDTO){
        if(employeeRepository.findById(id).isEmpty()) 
        {
            throw new NotFoundException("Employee not found");
        }

        if(editEmployeeDTO.getName() == null || editEmployeeDTO.getEmail() == null 
        || editEmployeeDTO.getMarketId() == null )
        {
            throw new BadRequestException("Some field of employee is null");
        }

        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setName(editEmployeeDTO.getName());
            existingEmployee.setEmail(editEmployeeDTO.getEmail());
            existingEmployee.setMarketId(editEmployeeDTO.getMarketId());
        }
        this.employeeRepository.save(existingEmployee);
        return existingEmployee;
    }
    
}
