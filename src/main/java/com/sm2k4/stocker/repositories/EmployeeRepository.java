package com.sm2k4.stocker.repositories;

import com.sm2k4.stocker.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
