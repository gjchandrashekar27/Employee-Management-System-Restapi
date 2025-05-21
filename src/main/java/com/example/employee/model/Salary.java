package com.example.employee.model;

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
@Table(name = "salaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "salary_id")
	    private Long salaryId;

	    @ManyToOne
	    @JoinColumn(name = "job_id", nullable = false)
	    private JobDepartment jobDepartment;

	    @Column(nullable = false)
	    private Double amount;

	    @Column(nullable = false)
	    private Double annual;

	    @Column(nullable = false)
	    private Double bonus;

}
