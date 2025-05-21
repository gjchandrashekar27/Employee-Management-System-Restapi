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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "leaves")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "leave_id")
	    private Long leaveId;

	    @Column(name = "leave_date", nullable = false)
	    private LocalDate leaveDate;

	    @Column(nullable = false)
	    private String reason;

	    @ToString.Exclude
	    @EqualsAndHashCode.Exclude
	    @ManyToOne
	    @JoinColumn(name = "emp_id", nullable = false)
	    private Employee employee;

}
