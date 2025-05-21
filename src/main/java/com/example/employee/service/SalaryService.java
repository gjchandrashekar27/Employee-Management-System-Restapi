package com.example.employee.service;

import java.util.List;

import com.example.employee.model.Salary;

public interface SalaryService {

	Salary addSalary(Salary salary, Long jobId);

	List<Salary> getAllSalaries();

	Salary getSalaryById(Long salaryId);

	List<Salary> getSalariesByJobId(Long jobId);

	Salary updateSalary(Long salaryId, Salary updatedSalary);

	void deleteSalary(Long salaryId);

	

	

}
