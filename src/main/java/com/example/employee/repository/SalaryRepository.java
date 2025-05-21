package com.example.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.model.JobDepartment;
import com.example.employee.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Long>{

	List<Salary> findByJobDepartment(JobDepartment job);

}
