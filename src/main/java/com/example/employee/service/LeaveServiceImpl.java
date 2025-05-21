package com.example.employee.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.exception.InvalidLeaveDateException;
import com.example.employee.exception.InvalidLeaveReasonException;
import com.example.employee.exception.LeaveAlreadyExistsException;
import com.example.employee.exception.LeaveNotFoundByIdException;
import com.example.employee.exception.LeaveNotFoundException;
import com.example.employee.exception.NoEmployeeFoundByIdException;
import com.example.employee.exception.NoLeavesAvailableException;
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

	@Override
	public List<Leave> getAllLeaves() {
	    List<Leave> leaves = leaveRepository.findAll();
	    if (leaves.isEmpty()) {
	        throw new NoLeavesAvailableException("No leave records available.");
	    }
	    return leaves;
	}
	
	@Override
	public Leave getLeaveById(Long id) {
		 return leaveRepository.findById(id)
		            .orElseThrow(() -> new LeaveNotFoundByIdException("Leave not found with id: " + id));
	}

	@Override
	public Leave updateLeave(Long id, Leave updatedLeave) {
	    Leave existingLeave = leaveRepository.findById(id)
	            .orElseThrow(() -> new LeaveNotFoundException("Leave not found with id: " + id));

	    // Validate new date
	    if (updatedLeave.getLeaveDate() == null || updatedLeave.getLeaveDate().isBefore(LocalDate.now())) {
	        throw new InvalidLeaveDateException("Leave date must be today or a future date.");
	    }

	    // Validate reason
	    if (updatedLeave.getReason() == null || updatedLeave.getReason().trim().isEmpty()) {
	        throw new InvalidLeaveReasonException("Reason for leave is required.");
	    }

	    // Validate employee
	    Long empId = updatedLeave.getEmployee().getEmpId();
	    Employee employee = employeeRepository.findById(empId)
	            .orElseThrow(() -> new NoEmployeeFoundByIdException("Employee not found with id: " + empId));

	    // Check if another leave already exists on the same date for this employee
	    boolean exists = leaveRepository.existsByEmployeeAndLeaveDate(employee, updatedLeave.getLeaveDate());
	    if (exists && !existingLeave.getLeaveDate().equals(updatedLeave.getLeaveDate())) {
	        throw new LeaveAlreadyExistsException("Leave already exists for employee on this date.");
	    }

	    existingLeave.setLeaveDate(updatedLeave.getLeaveDate());
	    existingLeave.setReason(updatedLeave.getReason());
	    existingLeave.setEmployee(employee);

	    return leaveRepository.save(existingLeave);
	}

	@Override
	public void deleteLeaveById(Long id) {
		 Leave leave = leaveRepository.findById(id)
		            .orElseThrow(() -> new LeaveNotFoundByIdException("Leave not found with id: " + id));
		    leaveRepository.delete(leave);
		
	}

	
}
