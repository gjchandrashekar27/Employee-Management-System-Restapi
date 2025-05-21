package com.example.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.model.Employee;
import com.example.employee.model.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification, Long>{

	List<Qualification> findByEmployee(Employee employee);

}
