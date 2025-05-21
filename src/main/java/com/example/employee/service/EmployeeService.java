package com.example.employee.service;

import java.util.List;
import com.example.employee.model.Employee;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long empId);

    Employee updateEmployee(Long empId, Employee employee);

    void deleteEmployee(Long empId);
}
