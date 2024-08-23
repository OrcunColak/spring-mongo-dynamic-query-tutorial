package com.colak.springtutorial.repository;

import com.colak.springtutorial.document.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ResourceRepository<Employee, String> {
}