package com.sm2k4.stocker.services;

import java.util.List;
import java.util.Optional;

import com.sm2k4.stocker.dtos.Employee.EmployeeDto;
import com.sm2k4.stocker.models.Market;
import com.sm2k4.stocker.repositories.MarketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.sm2k4.stocker.dtos.Employee.CreateEmployeeDTO;
import com.sm2k4.stocker.dtos.Employee.EditEmployeeDTO;
import com.sm2k4.stocker.exceptions.GeneralExceptions.BadRequestException;
import com.sm2k4.stocker.exceptions.GeneralExceptions.NotFoundException;
import com.sm2k4.stocker.models.Employee;
import com.sm2k4.stocker.repositories.EmployeeRepository;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MarketRepository marketRepository;

    public EmployeeService(EmployeeRepository employeeRepository , MarketRepository marketRepository){
        this.employeeRepository = employeeRepository;
        this.marketRepository = marketRepository;
    }

  
    public List<Employee> getAllEmployees(){

        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()){
            log.warn("Employees List is empty");
            throw new NotFoundException("Employees not found");
        }
        log.info("Fetched All the employees");
        return employees;

    }

    public Employee getEmployeeByID(long id){
        if(employeeRepository.findById(id).isEmpty())
        {
            log.warn("Employee with id {} not found", id);
             throw new NotFoundException("Employee not found");
        }
        
        log.info("Fetched Employee with id {}", id);
        return employeeRepository.findById(id).orElse(null);
    }


    public Employee createEmployee(CreateEmployeeDTO createEmployeeDTO){
        if(createEmployeeDTO.getName() == null || createEmployeeDTO.getEmail() == null 
            || createEmployeeDTO.getDepartment() == null ||
            createEmployeeDTO.getRole() == null || createEmployeeDTO.getMarketId() == null )
        {
            log.warn("Employee creation failed");
            throw new BadRequestException("Some field of employee is null");
        }
        Market market = marketRepository.findById(createEmployeeDTO.getMarketId()).orElse(null);

        Employee employee = new Employee();
        employee.setName(createEmployeeDTO.getName());
        employee.setEmail(createEmployeeDTO.getEmail());
        employee.setDepartment(createEmployeeDTO.getDepartment());
        employee.setRole(createEmployeeDTO.getRole());
        employee.setMarketId(market);

        log.info("New employee created");
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployeeByMarketId(Long marketId){
        Market market = marketRepository.findById(marketId).orElse(null);
        List<Employee> employee = employeeRepository.findAll();

        if(market == null){
            throw new NotFoundException("Market not found");
        }
        for (Employee emp : employee) {
            if(emp.getMarketId().getId() == marketId){
                employee.add(emp);
            }
        }
        return employee;
    }

    public void deleteEmployee(Long id){
        if(employeeRepository.findById(id).isEmpty())
        {
            log.warn("Employee with id {} not found", id);
            throw new NotFoundException("Employee not found");
        }

        log.info("Fetched Employee with id {}", id);
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(long id, EditEmployeeDTO editEmployeeDTO){
        if(employeeRepository.findById(id).isEmpty()) 
        {
            log.warn("Employee with id {} not found", id);
            throw new NotFoundException("Employee not found");
        }
        Market market = marketRepository.findById(editEmployeeDTO.getMarketId()).orElse(null);

        if(editEmployeeDTO.getName() == null || editEmployeeDTO.getEmail() == null 
        || editEmployeeDTO.getMarketId() == null )
        {
            log.info("Employee update failed");
            throw new BadRequestException("Some field of employee is null");
        }

        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(id);
        if (existingEmployeeOptional.isEmpty()){
            log.warn("Employee with id {} not found", id);
            throw new NotFoundException("No Employee Found.");
        }
        Employee existingEmployee = existingEmployeeOptional.get();

        existingEmployee.setEmail(editEmployeeDTO.getEmail());
        existingEmployee.setMarketId(market);
        existingEmployee.setName(editEmployeeDTO.getName());

        log.info("Fetched Employee with id {}", id);
        this.employeeRepository.save(existingEmployee);
        return existingEmployee;
    }
    
}
