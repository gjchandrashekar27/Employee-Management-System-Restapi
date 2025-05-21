package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.model.JobDepartment;
import com.example.employee.model.Payroll;
import com.example.employee.model.Salary;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.JobDepartmentRepository;
import com.example.employee.repository.PayrollRepository;
import com.example.employee.repository.SalaryRepository;

@Service
public class PayrollServiceImpl implements PayrollService {
	
	@Autowired
	JobDepartmentRepository jobDepartmentRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PayrollRepository payrollRepository;
	
	@Autowired
	SalaryRepository salaryRepository;

	@Override
	public Payroll addPayroll(Payroll payroll, Long empId, Long jobId, Long salaryId) {
		Employee employee = employeeRepository.findById(empId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));

	        JobDepartment jobDepartment = jobDepartmentRepository.findById(jobId)
	            .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));

	        Salary salary = salaryRepository.findById(salaryId)
	            .orElseThrow(() -> new ResourceNotFoundException("Salary not found with ID: " + salaryId));

	        payroll.setEmployee(employee);
	        payroll.setJobDepartment(jobDepartment);
	        payroll.setSalary(salary);

	        return payrollRepository.save(payroll);
		
	}

	@Override
	public List<Payroll> getAllPayrolls() {
		return payrollRepository.findAll();
	}

	@Override
	public Payroll updatePayroll(Long payrollId, Payroll updatedPayroll) {
		 Payroll existingPayroll = payrollRepository.findById(payrollId)
	                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found with ID: " + payrollId));

	        existingPayroll.setDate(updatedPayroll.getDate());
	        existingPayroll.setReport(updatedPayroll.getReport());
	        existingPayroll.setTotalAmount(updatedPayroll.getTotalAmount());

	        // Optionally update employee, job, salary if needed here

	        return payrollRepository.save(existingPayroll);
	}

	@Override
	public Payroll getPayrollById(Long payrollId) {
		 return payrollRepository.findById(payrollId)
	                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found with ID: " + payrollId));
	}

	@Override
	public void deletePayroll(Long payrollId) {
		 Payroll payroll = payrollRepository.findById(payrollId)
	                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found with ID: " + payrollId));
	        payrollRepository.delete(payroll);
	    }
		
	}
	

