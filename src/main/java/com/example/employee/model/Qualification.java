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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "qualification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Qualification {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "qual_id")  // primary key
	    private Long qualId;

	    @ManyToOne
	    @JoinColumn(name = "emp_id", nullable = false)  // foreign key to Employee
	    private Employee employee;

	    @NotBlank
	    @Column(nullable = false)
	    private String position;

	    @NotBlank
	    @Column(nullable = false)
	    private String requirements;

	    @PastOrPresent
	    @Column(name = "date_in", nullable = false)
	    private LocalDate dateIn;
	

}
