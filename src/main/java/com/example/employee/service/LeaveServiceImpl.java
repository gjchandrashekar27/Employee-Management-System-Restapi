package com.example.employee.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.exception.LeaveAlreadyExistsException;
import com.example.employee.exception.NoEmployeeFoundByIdException;
import com.example.employee.model.Employee;
import com.example.employee.model.Leave;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.LeaveRepository;

@Service
public class LeaveServiceImpl implements LeaveService{
	
	@Autowired
	LeaveRepository leaveRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Leave createLeave(Leave leave) {
		 // Validate leave date
        if (leave.getLeaveDate() == null || leave.getLeaveDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Leave date must be today or a future date.");
        }

        // Validate reason
        if (leave.getReason() == null || leave.getReason().trim().isEmpty()) {
            throw new IllegalArgumentException("Reason for leave is required.");
        }

        // Validate employee exists
        Long empId = leave.getEmployee().getEmpId();
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new NoEmployeeFoundByIdException("Employee not found with id: " + empId));

        leave.setEmployee(employee);

        // Check overlapping leave on the same date for this employee
        boolean exists = leaveRepository.existsByEmployeeAndLeaveDate(employee, leave.getLeaveDate());
        if (exists) {
            throw new LeaveAlreadyExistsException("Leave already exists for employee on this date.");
        }

        return leaveRepository.save(leave);
	}
	
	

}
