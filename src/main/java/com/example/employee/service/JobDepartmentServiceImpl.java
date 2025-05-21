package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.model.JobDepartment;
import com.example.employee.repository.JobDepartmentRepository;

@Service
public class JobDepartmentServiceImpl implements JobDepartmentService{
	
	@Autowired
	JobDepartmentRepository jobDepartmentRepository;

	@Override
	public JobDepartment saveJobDepartment(JobDepartment jobDepartment) {
		return jobDepartmentRepository.save(jobDepartment);
	}

	@Override
	public List<JobDepartment> getAllJobDepartments() {
		List<JobDepartment> list = jobDepartmentRepository.findAll();
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No JobDepartments found.");
        }
        return list;
	}

	@Override
	public JobDepartment getJobDepartmentById(Long jobId) {
		 return jobDepartmentRepository.findById(jobId)
	                .orElseThrow(() -> new ResourceNotFoundException("JobDepartment not found with ID: " + jobId));
	}

	@Override
	public JobDepartment updateJobDepartment(Long jobId, JobDepartment updatedJobDepartment) {
		 // Find the existing JobDepartment
		JobDepartment existing = jobDepartmentRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("JobDepartment not found with ID: " + jobId));

        existing.setJobDept(updatedJobDepartment.getJobDept());
        existing.setName(updatedJobDepartment.getName());
        existing.setDescription(updatedJobDepartment.getDescription());
        existing.setSalaryRange(updatedJobDepartment.getSalaryRange());

        return jobDepartmentRepository.save(existing);
	
	}

	@Override
	public void deleteJobDepartment(Long jobId) {
		// Check if the JobDepartment exists
		JobDepartment existing = jobDepartmentRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("JobDepartment not found with ID: " + jobId));
        jobDepartmentRepository.delete(existing);
		
	}

}
