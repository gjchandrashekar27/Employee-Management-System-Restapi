package com.example.employee.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payrolls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "payroll_id")
	    private Long payrollId;

	    @ManyToOne
	    @JoinColumn(name = "emp_id", nullable = false)
	    private Employee employee;

	    @ManyToOne
	    @JoinColumn(name = "job_id", nullable = false)
	    private JobDepartment jobDepartment;

	    @ManyToOne
	    @JoinColumn(name = "salary_id", nullable = false)
	    private Salary salary;

	    @Column(nullable = false)
	    private LocalDate date;

	    @Column(length = 1000)
	    private String report;

	    @Column(name = "total_amount", nullable = false)
	    private Double totalAmount;

}
