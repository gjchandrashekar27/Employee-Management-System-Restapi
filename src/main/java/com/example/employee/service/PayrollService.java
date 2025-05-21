package com.example.employee.service;

import java.util.List;

import com.example.employee.model.Payroll;

public interface PayrollService {

	Payroll addPayroll(Payroll payroll, Long empId, Long jobId, Long salaryId);

	List<Payroll> getAllPayrolls();

	Payroll updatePayroll(Long payrollId, Payroll updatedPayroll);

	Payroll getPayrollById(Long payrollId);

	void deletePayroll(Long payrollId);

}
