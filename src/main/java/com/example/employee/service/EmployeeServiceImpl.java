package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.exception.AgeShouldBeAbove18Exception;
import com.example.employee.exception.NoEmployeeFoundByIdException;
import com.example.employee.exception.NoEmployeesAvailableException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        if (employee.getAge() <= 18) {
            throw new AgeShouldBeAbove18Exception("Age should be above 18 to register employee.");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new NoEmployeesAvailableException("No employees found in the database.");
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId)
            .orElseThrow(() -> new NoEmployeeFoundByIdException("Employee not found with id: " + empId));
    }

    @Override
    public Employee updateEmployee(Long empId, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(empId)
            .orElseThrow(() -> new NoEmployeeFoundByIdException("Employee not found with id: " + empId));

        existingEmployee.setFname(employee.getFname());
        existingEmployee.setLname(employee.getLname());
        existingEmployee.setAge(employee.getAge());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long empId) {
        Employee existingEmployee = employeeRepository.findById(empId)
            .orElseThrow(() -> new NoEmployeeFoundByIdException("Cannot delete. Employee not found with id: " + empId));

        employeeRepository.delete(existingEmployee);
    }
}
