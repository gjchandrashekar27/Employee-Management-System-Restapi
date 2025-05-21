package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.model.JobDepartment;
import com.example.employee.model.Salary;
import com.example.employee.repository.JobDepartmentRepository;
import com.example.employee.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService {
	
	@Autowired
	JobDepartmentRepository jobDepartmentRepository;
	
	@Autowired
	SalaryRepository salaryRepository;

	@Override
	public Salary addSalary(Salary salary, Long jobId) {
		JobDepartment job = jobDepartmentRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));

        salary.setJobDepartment(job);
        return salaryRepository.save(salary);
	}

	@Override
	public List<Salary> getAllSalaries() {
		 return salaryRepository.findAll(); // returns [] if nothing found
	}

	@Override
	public Salary getSalaryById(Long salaryId) {
		 return salaryRepository.findById(salaryId)
		            .orElseThrow(() -> new ResourceNotFoundException("Salary not found with ID: " + salaryId));
	}

	@Override
	public List<Salary> getSalariesByJobId(Long jobId) {
		JobDepartment job = jobDepartmentRepository.findById(jobId)
	            .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));

	    return salaryRepository.findByJobDepartment(job);
	}

	@Override
	public Salary updateSalary(Long salaryId, Salary updatedSalary) {
		Salary existingSalary = salaryRepository.findById(salaryId)
		        .orElseThrow(() -> new ResourceNotFoundException("Salary not found with ID: " + salaryId));

		    existingSalary.setAmount(updatedSalary.getAmount());
		    existingSalary.setAnnual(updatedSalary.getAnnual());
		    existingSalary.setBonus(updatedSalary.getBonus());

		    // Optional: Update jobDepartment if needed
		    if (updatedSalary.getJobDepartment() != null) {
		        existingSalary.setJobDepartment(updatedSalary.getJobDepartment());
		    }

		    return salaryRepository.save(existingSalary);
	}

	@Override
	public void deleteSalary(Long salaryId) {
		 Salary salary = salaryRepository.findById(salaryId)
			        .orElseThrow(() -> new ResourceNotFoundException("Salary not found with ID: " + salaryId));
			    salaryRepository.delete(salary);
		
	}

}
