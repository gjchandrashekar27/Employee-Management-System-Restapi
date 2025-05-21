package com.example.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.model.JobDepartment;

public interface JobDepartmentRepository  extends JpaRepository<JobDepartment, Long>{

}
