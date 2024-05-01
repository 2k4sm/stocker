package com.sm2k4.stocker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sm2k4.stocker.models.Market;
import com.sm2k4.stocker.models.Stock;
import com.sm2k4.stocker.repositories.MarketRepository;
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
    private final MarketRepository marketRepository;

    public EmployeeService(EmployeeRepository employeeRepository , MarketRepository marketRepository){
        this.employeeRepository = employeeRepository;
        this.marketRepository = marketRepository;
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
        Market market = marketRepository.findById(createEmployeeDTO.getMarketId()).orElse(null);

        Employee employee = new Employee();
        employee.setName(createEmployeeDTO.getName());
        employee.setEmail(createEmployeeDTO.getEmail());
        employee.setDepartment(createEmployeeDTO.getDepartment());
        employee.setRole(createEmployeeDTO.getRole());
        employee.setMarketId(market);

        return employeeRepository.save(employee);
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
        Market market = marketRepository.findById(editEmployeeDTO.getMarketId()).orElse(null);

        if(editEmployeeDTO.getName() == null || editEmployeeDTO.getEmail() == null 
        || editEmployeeDTO.getMarketId() == null )
        {
            throw new BadRequestException("Some field of employee is null");
        }

        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setName(editEmployeeDTO.getName());
            existingEmployee.setEmail(editEmployeeDTO.getEmail());
            existingEmployee.setMarketId(market);
        }
        this.employeeRepository.save(existingEmployee);
        return existingEmployee;
    }
    
}
