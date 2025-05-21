package com.example.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.model.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long>{

}
