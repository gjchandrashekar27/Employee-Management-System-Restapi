package com.example.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data         // Lombok: generates getters, setters, toString, etc.
@NoArgsConstructor  // Lombok: no-arg constructor
@AllArgsConstructor  // Lombok: all-args constructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "emp_id")  // aligns with your ER diagram
    private Long empId;
	
	@NotBlank
	@Column(name = "first_name", nullable = false)
	private String fname;
	
	@NotBlank
	@Column(name = "last_name", nullable = false)
	private String lname;
	
	@Min(18)
	@Max(60)
	@Column(nullable = false)
	private int age;
	
	

}
