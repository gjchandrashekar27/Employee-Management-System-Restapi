package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Salary;
import com.example.employee.service.SalaryService;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {
	
	@Autowired
	SalaryService salaryService;
	
	// Create a new salary entry for a job
    @PostMapping("/{jobId}")
    public ResponseEntity<Salary> createSalary(@PathVariable Long jobId,
                                               @RequestBody Salary salary) {
        Salary savedSalary = salaryService.addSalary(salary, jobId);
        return new ResponseEntity<>(savedSalary, HttpStatus.CREATED);
    }

    // Get all salary records
    @GetMapping
    public ResponseEntity<List<Salary>> getAllSalaries() {
        List<Salary> salaries = salaryService.getAllSalaries();
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

    // Get a salary by its ID
    @GetMapping("/{salaryId}")
    public ResponseEntity<Salary> getSalaryById(@PathVariable Long salaryId) {
        Salary salary = salaryService.getSalaryById(salaryId);
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }

    // Get all salary records by job ID
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Salary>> getSalariesByJobId(@PathVariable Long jobId) {
        List<Salary> salaries = salaryService.getSalariesByJobId(jobId);
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

    // Update a salary record by ID
    @PutMapping("/{salaryId}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long salaryId,
                                               @RequestBody Salary updatedSalary) {
        Salary salary = salaryService.updateSalary(salaryId, updatedSalary);
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }

    // Delete a salary record by ID
    @DeleteMapping("/{salaryId}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long salaryId) {
        salaryService.deleteSalary(salaryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	
}
