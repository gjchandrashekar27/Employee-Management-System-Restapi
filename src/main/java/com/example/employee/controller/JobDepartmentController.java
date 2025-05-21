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

import com.example.employee.model.JobDepartment;
import com.example.employee.service.JobDepartmentService;

@RestController
@RequestMapping("/api/jobdepartments")
public class JobDepartmentController {
	
	@Autowired
	JobDepartmentService jobDepartmentService;
	
	@PostMapping
    public ResponseEntity<JobDepartment> createJobDepartment(@RequestBody JobDepartment jobDepartment) {
        JobDepartment saved = jobDepartmentService.saveJobDepartment(jobDepartment);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Get all JobDepartments
    @GetMapping
    public ResponseEntity<List<JobDepartment>> getAllJobDepartments() {
        List<JobDepartment> list = jobDepartmentService.getAllJobDepartments();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Get JobDepartment by jobId
    @GetMapping("/{jobId}")
    public ResponseEntity<JobDepartment> getJobDepartmentById(@PathVariable Long jobId) {
        JobDepartment jobDepartment = jobDepartmentService.getJobDepartmentById(jobId);
        return new ResponseEntity<>(jobDepartment, HttpStatus.OK);
    }

    // Update JobDepartment
    @PutMapping("/{jobId}")
    public ResponseEntity<JobDepartment> updateJobDepartment(@PathVariable Long jobId,
                                                             @RequestBody JobDepartment updatedJobDepartment) {
        JobDepartment updated = jobDepartmentService.updateJobDepartment(jobId, updatedJobDepartment);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Delete JobDepartment
    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJobDepartment(@PathVariable Long jobId) {
        jobDepartmentService.deleteJobDepartment(jobId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
