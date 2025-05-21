package com.example.employee.service;

import java.util.List;

import com.example.employee.model.JobDepartment;

public interface JobDepartmentService {

	JobDepartment saveJobDepartment(JobDepartment jobDepartment);

	List<JobDepartment> getAllJobDepartments();

	JobDepartment getJobDepartmentById(Long jobId);

	JobDepartment updateJobDepartment(Long jobId, JobDepartment updatedJobDepartment);

	void deleteJobDepartment(Long jobId);

}
